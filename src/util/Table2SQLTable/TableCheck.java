package util.Table2SQLTable;

import Beans.Section;
import DAO.DBConnections;
import DAO.SectionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TableCheck {
    public static void checkCourseTitle(Connection conn)throws Exception{
        String sql="select * from course where title=''";

        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("title 不能为空");
        }
    }
    public static void checkSectionTime(Connection conn)throws Exception{
        String sql="with sec_time as "+
                "(select courseid,sectionid,day,starttime,endtime from section natural join timeslot) ";
        sql+="select * from  sec_time as S join sec_time as T "+
                "on S.courseid=T.courseid and S.sectionid=T.sectionid and not (S.day = T.day and S.starttime=T.starttime)";
        sql+="where S.day=T.day and S.starttime<=T.starttime and T.starttime<=S.endtime ";

        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("课程 "+rs.getString("courseid")+"."
                    +rs.getString("sectionid")+"存在时间冲突");
        }

    }
    public static void checkTimeslot(Connection conn)throws Exception{
        String sql="select * from timeslot as S join timeslot as T ";
        sql+="on S.timeslotid=T.timeslotid and not (S.day=T.day and S.starttime=T.starttime)";
        sql+="where S.day=T.day and S.starttime<=T.starttime and T.starttime<=S.endtime  ";

        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("timeslot: "+rs.getString("timeslotid")+" 存在时间冲突");
        }
    }
    public static void checkTeacherTime(Connection conn)throws Exception{
        String sql="with teacher_time as "+
                "(select teacherid,courseid,sectionid,day,starttime,endtime "+
                "from teacher natural join teaches natural join section natural join timeslot)";
        sql+="select * from teacher_time as S join teacher_time as T "
                +"on S.teacherid=T.teacherid and not (S.courseid=T.courseid and S.sectionid=T.sectionid)";
        sql+="where S.day =T.day and S.starttime<=T.starttime and T.starttime<=S.endtime";
        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("教师 "+rs.getString("teacherid")+
                    "在"+rs.getString("S.courseid")+"." +
                    rs.getString("S.sectionid")+"和"+rs.getString("T.courseid")
                    +"."+rs.getString("T.sectionid")+"存在时间冲突");
        }
    }
    public static void checkStudentTime(Connection conn)throws Exception{
        String sql="with student_time as (select studentid,courseid,sectionid,day,starttime,endtime "+
                "from student natural join takes natural join section natural join timeslot)";
        sql+="select * from student_time as S join student_time as T "
                +"on S.studentid=T.studentid and not (S.courseid=T.courseid and S.sectionid=T.sectionid)";
        sql+="where S.day =T.day and S.starttime<=T.starttime and T.starttime<=S.endtime";
        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("学生 "+rs.getString("studentid")+
                    "在"+rs.getString("S.courseid")+"." +
                    rs.getString("S.sectionid")+"和"+rs.getString("T.courseid")
                    +"."+rs.getString("T.sectionid")+"存在时间冲突");
        }
    }
    public static void checkClassroomTime(Connection conn)throws Exception{
        String sql="with classroom_time as "+
                "(select building,roomnumber,courseid,sectionid,day,starttime,endtime "+
                "from classroom natural join section natural join timeslot)";
        sql+="select * from classroom_time as S join classroom_time as T "
                +"on S.building=T.building and S.roomnumber=T.roomnumber and not (S.courseid=T.courseid and S.sectionid=T.sectionid)";
        sql+="where S.day =T.day and S.starttime<=T.starttime and T.starttime<=S.endtime";
        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("教室 "+rs.getString("building")+rs.getString("roomnumber")
                    +"在"+rs.getString("S.courseid")+"." +
                    rs.getString("S.sectionid")+"和"+rs.getString("T.courseid")
                    +"."+rs.getString("T.sectionid")+"存在时间冲突");
        }
    }
//    public static void checkExamTime(Connection conn)throws Exception{
//        String sql="select * from exam as S join exam as T on not ( S.courseid=T.courseid and S.sectionid=T.sectionid ) ";
//        sql+="where S.examday =T.examday and S.examstarttime<=T.examstarttime and T.examstarttime<=S.examendtime";
//        PreparedStatement stat=conn.prepareStatement(sql);
//        ResultSet rs=stat.executeQuery();
//        if(rs.next()){
//            throw new RuntimeException("考试 "+rs.getString("S.courseid")+"." +
//                    rs.getString("S.sectionid")+"和"+rs.getString("T.courseid")
//                    +"."+rs.getString("T.sectionid")+"存在时间冲突");
//        }
//    }
    public static void checkExamClassroomTime(Connection conn)throws Exception{
        String sql="select * from exam as S join exam as T on S.building=T.building and S.roomnumber=T.roomnumber and " +
                "not ( S.courseid=T.courseid and S.sectionid=T.sectionid ) ";
        sql+="where S.examday =T.examday and S.examstarttime<=T.examstarttime and T.examstarttime<=S.examendtime";
        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException(rs.getString("S.courseid")+"." +
                    rs.getString("S.sectionid")+"的考试教室和"+rs.getString("T.courseid")
                    +"."+rs.getString("T.sectionid")+"的教室存在时间冲突");
        }
    }
    public static void checkSectionStudentLimitLessThanClassroomcapacity(Connection conn)throws Exception{
        String sql="select * from section natural join classroom where capacity<studentnumberlimit";
        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException(rs.getString("courseid")+"."+rs.getString("sectionid")+"最大人数大于教室容量");
        }
    }
    public static boolean checkStudentExamTime(String studentid)throws Exception{
        String sql="with student_examtime as (select studentid,courseid,sectionid,examday,examstarttime,examendtime "+
                "from student natural join takes natural join exam "+
                "where studentid=? )";
        sql+="select * from student_examtime as S join student_examtime as T "
                +"on S.studentid=T.studentid and not (S.courseid=T.courseid and S.sectionid=T.sectionid)";
        sql+="where S.examday =T.examday and S.examstarttime<=T.examstarttime and T.examstarttime<=S.examendtime";
        Connection conn= DBConnections.borrowConnection();
        PreparedStatement stat=conn.prepareStatement(sql);
        stat.setObject(1,studentid);
        ResultSet rs=stat.executeQuery();
        boolean pass=!rs.next();
        DBConnections.returnConnection(conn);
        return pass;
    }
    public static boolean checkStudentTime(String studentid)throws Exception{
        String sql="with student_time as (select studentid,courseid,sectionid,day,starttime,endtime "+
                "from student natural join takes natural join section natural join timeslot "+
                "where studentid=? )";
        sql+="select * from student_time as S join student_time as T "
                +"on S.studentid=T.studentid and not (S.courseid=T.courseid and S.sectionid=T.sectionid)";
        sql+="where S.day =T.day and S.starttime<=T.starttime and T.starttime<=S.endtime";
        Connection conn= DBConnections.borrowConnection();
        PreparedStatement stat=conn.prepareStatement(sql);
        stat.setObject(1,studentid);
        ResultSet rs=stat.executeQuery();
        boolean pass=!rs.next();
        DBConnections.returnConnection(conn);
        return pass;
    }
    public static String checkCanPassRequest(String studentid,String courseid,String sectionid){
        Section section=new SectionDAO().getSectionByCourseAndSectionid(courseid,sectionid);
        if(section==null){
            return "没有这个课";
        }

        return null;
    }
}
