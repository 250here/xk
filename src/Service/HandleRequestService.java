package Service;

import Beans.Classroom;
import Beans.Request;
import Beans.Section;
import DAO.*;
import util.Table2SQLTable.TableCheck;

import java.sql.Connection;

public class HandleRequestService {
    public static String checkRequests(Request request){
        Section section=new SectionDAO().getSectionByCourseAndSectionid(request.getCourseId(),request.getSectionId());
        Classroom classroom=new ClassroomDAO().getClassroom(section.getBuilding(),section.getRoomNumber());

        //有余量自动通过
        if(section.getStudentNumberLimit()>section.getNumberOfStudent()){
            String result=passRequest(request);
            if(result==null){
                return "已自动通过";
            }else {
                return null;
            }
        }
        //超教室容量上限自动驳回
        if(section.getNumberOfStudent()>=classroom.getCapacity()){
            new RequestDAO().updateState(request,"refuse");
            return "自动拒绝";
        }
        return null;
    }
    public static String passRequest(Request request){
        Section section=new SectionDAO().getSectionByCourseAndSectionid(request.getCourseId(),request.getSectionId());
        //System.out.println(""+request.getStudentId()+section.getCourseId()+section.getSectionId());
        //new TakesDAO().insertSectionToTakes(request.getStudentId(),section);
        Connection conn= DBConnections.borrowConnection();
        boolean commit=false;
        try{
            conn.setAutoCommit(false);
            DBConnections.executeSql("insert into takes values(?,?,?,'')"
            ,section.getCourseId(),section.getSectionId(),request.getStudentId());
            boolean pass=true;
            pass=pass&&TableCheck.checkStudentExamTime(request.getStudentId());
            pass=pass&&TableCheck.checkStudentTime(request.getStudentId());
            if(!pass){
                throw new RuntimeException("时间冲突");
            }
            conn.commit();
            commit=true;
            conn.setAutoCommit(true);
            //System.out.println("accept");
            new RequestDAO().updateState(request,"accept");
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }finally {
            try{
                if(!commit){
                    conn.rollback();
                }
                conn.setAutoCommit(true);
                if(!commit){
                    //System.out.println("??");
                    new TakesDAO().deleteSectionFromTakes(request.getStudentId(),section);
                    new RequestDAO().updateState(request,"refuse");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            DBConnections.returnConnection(conn);
        }
    }
}
