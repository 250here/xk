package DAO;

import Beans.Section;
import Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TeachesDAO {
    public ArrayList<Section> getSectionByTeacherid(String teacherid){          //根据教师id获得所教课程
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from teaches natural join course natural join section where teacherid=?";
        ArrayList<Section> sections = new ArrayList<>();

        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,teacherid);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                String courseid = rs.getString("courseid");
                String sectionid = rs.getString("sectionid");
                String sectionName = rs.getString("title");
                String building = rs.getString("building");
                String roomnuber = rs.getString("roomnumber");
                String examType = rs.getString("examtype");
                int numberOfStudent = rs.getInt("numberofstudent");
                int stuentNumberLimit = rs.getInt("studentnumberlimit");
                int timeSlotId = rs.getInt("timeslotid");

                Section section = new Section(courseid,sectionid);

                section.setSectionName(sectionName);
                section.setBuilding(building);
                section.setExamType(examType);
                section.setRoomNumber(roomnuber);
                section.setTimeSlotId(timeSlotId);
                section.setNumberOfStudent(numberOfStudent);
                section.setStudentNumberLimit(stuentNumberLimit);

                sections.add(section);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return sections;
    }
    public ArrayList<User> getTeacherBySectionid(String courseid,String sectionId){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from teaches natural join teacher where courseid=? and sectionid=?";
        ArrayList<User> teachers = new ArrayList<>();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionId);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                String teacherid = rs.getString("teacherid");
                String teacherName = rs.getString("teachername");
                String teacherpassword = rs.getString("teacherpassword");
                User teacher = new User(teacherid,teacherName,teacherpassword);
                teachers.add(teacher);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return teachers;
    }
    public void insert(String teacherid,String courseid,String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="insert into teaches values (?,?,?)";
        DBConnections.executeSql(sql,teacherid,courseid,sectionid);
    }
    public void delete(String courseid,String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="delete from teaches where courseid=? and sectionid=?";
        DBConnections.executeSql(sql,courseid,sectionid);
    }
}
