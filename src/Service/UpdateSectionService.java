package Service;

import Beans.Classroom;
import Beans.Section;
import DAO.ClassroomDAO;
import DAO.DBConnections;
import DAO.SectionDAO;
import util.Encode;
import util.Table2SQLTable.TableCheck;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UpdateSectionService {
    public static String updateSection(HttpServletRequest request)throws Exception{

        ClassroomDAO classroomDAO = new ClassroomDAO();
        SectionDAO sectionDAO = new SectionDAO();

        String id = request.getParameter("id");
        String numberlimit = request.getParameter("numberlimit");
        String building = Encode.parseToUTF8(request.getParameter("building"));
        String roomnumber =  Encode.parseToUTF8(request.getParameter("roomnumber"));

        String courseid ="";
        String sectionid = "";
        if(id.contains(".")){
            String[] s = id.split("\\.");
            courseid = s[0];
            sectionid = s[1];
        }

//        Section section = new Section(courseid,sectionid);
        String re = "";
        Connection conn= DBConnections.borrowConnection();
        boolean commit=false;
        try{
            List<String> names=new ArrayList<>();
            List<String> values=new ArrayList<>();
            if(!building.equals("")&&!roomnumber.equals("")){
                names.add("building");
                values.add(building);
                names.add("roomnumber");
                values.add(roomnumber);
            }
            if(!numberlimit.equals("")){
                names.add("studentnumberlimit");
                values.add(numberlimit);
            }
            String sql="update section set ";
            int len=names.size();
            if(len==0){
                return "无修改";
            }
            for(int i=0;i<len;i++){
                sql+=names.get(i);
                sql+="=?";
                //sql+=values.get(i);
                if(i<len-1){
                    sql+=",";
                }
            }
            sql+=" where courseid=? and sectionid=? ";
            values.add(courseid);
            values.add(sectionid);
            conn.setAutoCommit(false);
            DBConnections.executeSql(conn,sql,values);
            //check
            TableCheck.checkClassroomTime(conn);
            TableCheck.checkSectionStudentLimitLessThanClassroomcapacity(conn);
            commit=true;
            conn.commit();
            conn.setAutoCommit(true);
            return "修改成功";

        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }finally {
            if(!commit){
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            DBConnections.returnConnection(conn);
        }
//        if(!building.equals("")&&!roomnumber.equals("")){
//            Classroom classroom = new Classroom(building,roomnumber);
//            if(classroomDAO.haveClassroom(classroom)){
//                Section newsection = sectionDAO.getSectionByCourseAndSectionid(courseid,sectionid);
//                re = sectionDAO.updateSectionBuilding(newsection,building,roomnumber);
//                if(!re.equals("修改成功")){
//                    return re;
//                }
//            }
//        }
//        if(!numberlimit.equals("")){
//            int limit = Integer.parseInt(numberlimit);
//            Section newsection = sectionDAO.getSectionByCourseAndSectionid(courseid,sectionid);
//            re = sectionDAO.updateSectionLimit(newsection,limit);
//            if(!re.equals("修改成功")){
//                return re;
//            }
//        }
//        return re;
    }
}
