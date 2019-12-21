package DAO;

import Beans.Classroom;
import Beans.Exam;

import java.sql.Connection;

public class ExamDAO {
    public void insertExam(Exam exam){
        Connection conn=DBConnections.borrowConnection();
        String sql="insert into exam values (?,?,?,?,?,?,?)";
        DBConnections.executeSql(sql,exam.getCourseid(),exam.getSectionid(),exam.getBuilding(),exam.getRoomnumber(),exam.getExamday(),exam.getExamstarttime(),exam.getExamendtime());
    }
    public Classroom getExamRoom(String courseid,String sectionid){
//        Connection conn=DBConnections.borrowConnection();
//        String sql="select * from timeslot where timeslotid=?";
        return null;
    }
}
