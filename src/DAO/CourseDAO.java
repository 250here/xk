package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseDAO {
    public String getCourseNameByID(String courseid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from course where courseid=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String courseName = rs.getString("title");
//                System.out.println("b"+courseName);
                return courseName;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return null;
    }
}
