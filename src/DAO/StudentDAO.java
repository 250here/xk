package DAO;

import Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    public User getUserByStudenetID(String studentid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from student where studentid=?";
        User user=null;
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,studentid);
            ResultSet rs=    stmt.executeQuery();
            if(rs.next()){
                String passwd=rs.getString("studentpassword");
                assert passwd!=null;
                String name=rs.getString("studentname");
                user=new User(studentid,name,passwd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        //assert user!=null;
        return user;
    }
}
