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
        String sql="select * from takes where studentid=?";
        ArrayList<Section> sections = new ArrayList<Section>();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,studentId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String courseid = rs.getString("courseid");
                String sectionid = rs.getString("sectionid");

                Section section = new Section(courseid,sectionid);
                sections.add(section);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return sections;
    }

    public ArrayList<User> getStudentsBySectionid(String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from takes where sectionid=?";
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,sectionid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
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
        DBConnections.executeSql(sql,section.getCourseId(),section.getSectionId(),studentid,null);
    }
    public void deleteSectionFromTakes(String studentid,Section section){
        Connection conn=DBConnections.borrowConnection();
        String sql="delete from takes where courseid=? and sectionid=? and studentid=?";
        DBConnections.executeSql(sql,section.getCourseId(),section.getSectionId(),studentid);
    }
}
