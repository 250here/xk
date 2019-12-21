package DAO;

import Beans.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SectionDAO {
   public Section getSectionByCourseAndSectionid(String courseid,String sectionid){
       Connection conn=DBConnections.borrowConnection();
       String sql="select * from section natural join course where courseid=? and sectionid=?";
       Section section = null;

       try{
           PreparedStatement stmt= conn.prepareStatement(sql);
           stmt.setObject(1,courseid);
           stmt.setObject(2,sectionid);
           ResultSet rs = stmt.executeQuery();
           while (rs.next()){
               String secctionname =rs.getString("title");
               int credits = rs.getInt("credits");
               String buildig = rs.getString("building");
               String roomnuber = rs.getString("roomnumber");
               String examType = rs.getString("examtype");
               int numberOfStudent = rs.getInt("numberofstudent");
               int stuentNumberLimit = rs.getInt("studentnumberlimit");
               int timeSlotId = rs.getInt("timeslotid");


               section = new Section(courseid,sectionid);
               section.setSectionName(secctionname);
               section.setCredits(credits);
               section.setBuilding(buildig);
               section.setExamType(examType);
               section.setRoomNumber(roomnuber);
               section.setTimeSlotId(timeSlotId);
               section.setNumberOfStudent(numberOfStudent);
               section.setStudentNumberLimit(stuentNumberLimit);
               return section;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       DBConnections.returnConnection(conn);
       return section;
   }
   public ArrayList<Section> searchSection(String partName){
       Connection conn=DBConnections.borrowConnection();
       String sql="select * from section natural join course where title like ? or courseid like ? or sectionid like ?";
       ArrayList<Section> restule = new ArrayList<Section>();
       try{
           PreparedStatement stmt= conn.prepareStatement(sql);
           stmt.setObject(1,"%"+partName+"%");
           stmt.setObject(2,"%"+partName+"%");
           stmt.setObject(3,"%"+partName+"%");

           ResultSet rs = stmt.executeQuery();
           while (rs.next()){
               String sectionName = rs.getString("title");
               String courseid = rs.getString("courseid");
               String sectionid = rs.getString("sectionid");
               String buildig = rs.getString("building");
               String roomnuber = rs.getString("roomnumber");
               String examType = rs.getString("examtype");
               int numberOfStudent = rs.getInt("numberofstudent");
               int stuentNumberLimit = rs.getInt("studentnumberlimit");
               int timeSlotId = rs.getInt("timeslotid");
               int credits = rs.getInt("credits");
//               System.out.println("aaaaa"+courseid+sectionid+buildig+roomnuber+examType+numberOfStudent);
               Section section = new Section(courseid,sectionid);
               section.setSectionName(sectionName);
               section.setBuilding(buildig);
               section.setExamType(examType);
               section.setRoomNumber(roomnuber);
               section.setTimeSlotId(timeSlotId);
               section.setNumberOfStudent(numberOfStudent);
               section.setStudentNumberLimit(stuentNumberLimit);
               section.setCredits(credits);

               restule.add(section);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       DBConnections.returnConnection(conn);
       return  restule;
   }
   public void insertSetion(Section section){
       Connection conn=DBConnections.borrowConnection();
       String sql="insert into section values (?,?,?,?,?,?,?,?)";
       DBConnections.executeSql(sql,section.getCourseId(),section.getSectionId(),section.getBuilding(),section.getRoomNumber(),section.getNumberOfStudent(),section.getExamType(),section.getTimeSlotId(),section.getStudentNumberLimit());

   }
   public void deleteSection(String courseid,String sectionid){
       Connection conn=DBConnections.borrowConnection();
       String sql="delete from section where courseid=? and sectionid=?";
       DBConnections.executeSql(sql,courseid,sectionid);
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
    public boolean isInCourse(Section section){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from course where courseid=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,section.getCourseId());

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return false;
    }
    public boolean haveSection(Section section){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from section where courseid=? and sectionid=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,section.getCourseId());
            stmt.setObject(2,section.getSectionId());

            ResultSet rs=    stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return false;
    }
}
