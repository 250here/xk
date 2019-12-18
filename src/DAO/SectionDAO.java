package DAO;

import Beans.Section;
import Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SectionDAO {
   public Section getSecyionBySectionid(String sectionid){
       Connection conn=DBConnections.borrowConnection();
       String sql="select * from section where sectionid=?";
       Section section = null;

       try{
           PreparedStatement stmt= conn.prepareStatement(sql);
           stmt.setObject(1,sectionid);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()){
               String courseid = rs.getString("courseid");
               String buildig = rs.getString("building");
               String roomnuber = rs.getString("roomnumber");
               String examType = rs.getString("examtype");
               int numberOfStudent = rs.getInt("numberofstudent");
               int stuentNumberLimit = rs.getInt("studentnumberlimit");
               int timeSlotId = rs.getInt("timeslotid");


               section = new Section(courseid,sectionid);
               section.setBuilding(buildig);
               section.setExanType(examType);
               section.setRoomNumber(roomnuber);
               section.setTimeSlotId(timeSlotId);
               section.setNumberOfStudent(numberOfStudent);
               section.setStudentNumberLimit(stuentNumberLimit);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       DBConnections.returnConnection(conn);
       return section;
   }
   public void updataLimit(Section section,int limit){
       Connection conn=DBConnections.borrowConnection();
       String sql="update section set studentnumberlimit=? where courseid=? and sectionid=?";
       DBConnections.executeSql(sql,section.getCourseId(),limit,section.getSectionId());
   }
    public void updataNumber(Section section,int number){
        Connection conn=DBConnections.borrowConnection();
        String sql="update section set numberofstudent=? where courseid=? and sectionid=?";
        DBConnections.executeSql(sql,section.getCourseId(),number,section.getSectionId());
    }
}
