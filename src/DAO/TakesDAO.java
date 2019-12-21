package DAO;

import Beans.Section;
import Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TakesDAO {
    public ArrayList<Section> getSectionByStudentId(String studentId){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from takes natural join section natural join course where studentid=?";
        ArrayList<Section> sections = new ArrayList<Section>();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String courseid = rs.getString("courseid");
                String sectionid = rs.getString("sectionid");
                String sectionName = rs.getString("title");
                String building = rs.getString("building");
                String roomNumber = rs.getString("roomnumber");
                int timeSlotId = rs.getInt("timeslotid");


                Section section = new Section(courseid,sectionid);
                section.setSectionName(sectionName);
                section.setBuilding(building);
                section.setRoomNumber(roomNumber);
                section.setTimeSlotId(timeSlotId);

                sections.add(section);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return sections;
    }

    public ArrayList<User> getStudentsBySectionid(String courseid,String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from takes where courseid=? and sectionid=?";
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
//                String courseid = rs.getString("courseid");
                String studentid = rs.getString("studentid");

                User user = new User(studentid);
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return users;
    }
    public void insertSectionToTakes(String studentid,Section section){
        Connection conn=DBConnections.borrowConnection();
        String sql="insert into takes values (?,?,?,?)";
        DBConnections.executeSql(sql,section.getCourseId(),section.getSectionId(),studentid,"");
    }
    public void deleteSectionFromTakes(String studentid,Section section){
        Connection conn=DBConnections.borrowConnection();
        String sql="delete from takes where courseid=? and sectionid=? and studentid=?";
        DBConnections.executeSql(sql,section.getCourseId(),section.getSectionId(),studentid);
    }
    public boolean hadTakes(String courseid,String sectionid,String studentid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from takes where courseid=? and sectionid=? and studentid=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionid);
            stmt.setObject(3,studentid);
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
