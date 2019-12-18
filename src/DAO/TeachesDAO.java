package DAO;

import Beans.Section;
import Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TeachesDAO {
    public ArrayList<Section> getSectionByTeacherid(String teacherid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from teaches where teacherid=?";
        ArrayList<Section> sections = null;
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,teacherid);
            ResultSet rs= stmt.executeQuery();
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
        //assert user!=null;
        return sections;
    }
}
