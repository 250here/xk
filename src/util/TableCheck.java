package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TableCheck {

    public static void checkSectionTime(Connection conn)throws Exception{
        String sql="with sec_time as "+
                "(select courseid,sectionid,day,starttime,endtime from section natural join timeslot) ";
        sql+="select * from  sec_time as S join sec_time as T "+
                "on S.courseid=T.courseid and S.sectionid=T.sectionid and not (S.day = T.day and S.starttime=T.starttime)";
        sql+="where S.day=T.day and S.starttime<T.starttime and T.starttime<=S.endtime ";

        PreparedStatement stat=conn.prepareStatement(sql);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            throw new RuntimeException("课程 "+rs.getString("courseid")+"."
                    +rs.getString("sectionid")+"存在时间冲突");
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
        String sql="with student_time as "+
                "(select studentid,courseid,sectionid,day,starttime,endtime "+
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
}
