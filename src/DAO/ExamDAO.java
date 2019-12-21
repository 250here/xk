package DAO;

import Beans.Classroom;
import Beans.Exam;
import Beans.TimeSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExamDAO {
    public void insertExam(Exam exam){
        Connection conn=DBConnections.borrowConnection();
        String sql="insert into exam values (?,?,?,?,?,?,?)";
        DBConnections.executeSql(sql,exam.getCourseid(),exam.getSectionid(),exam.getBuilding(),exam.getRoomnumber(),exam.getExamday(),exam.getExamstarttime(),exam.getExamendtime());
    }
    public TimeSlot getExamTime(String courseid,String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from exam where courseid=? and sectionid=?";

        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int day = rs.getInt("examday");
                int startTime = rs.getInt("examstarttime");
                int endtime = rs.getInt("examendtime");

                TimeSlot timeSlot = new TimeSlot(0000000);  ////id任意，不插入数据库
                timeSlot.setDay(day);
                timeSlot.setStartTime(startTime);
                timeSlot.setEndTime(endtime);

               return timeSlot;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return new TimeSlot(0000000);
    }
    public Classroom getExamRoom(String courseid,String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from exam natural join classroom where courseid=? and sectionid=?";

        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
           String building = rs.getString("building");
           String roomnumber = rs.getString("roomnumber");
           int capacity = rs.getInt("capacity");

           Classroom classroom = new Classroom();
           classroom.setBuilding(building);
           classroom.setRoomnumber(roomnumber);
           classroom.setCapacity(capacity);
           return classroom;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return new Classroom();
    }
    }

