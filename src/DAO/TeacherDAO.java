package DAO;

import Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAO {
    public User getUserByTeacherID(String teacherid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from teacher where teacherid=?";
        User user=null;
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,teacherid);
            ResultSet rs=    stmt.executeQuery();
            if(rs.next()){
                String passwd=rs.getString("teacherpassword");
                assert passwd!=null;
                String name=rs.getString("teachername");
                user=new User(teacherid,name,passwd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        //assert user!=null;
        return user;
    }
    public boolean hasTeacher(User user){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from teacher where teacherid=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,user.id);
            ResultSet rs=    stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        //assert user!=null;
        return false;
    }
}
