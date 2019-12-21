package DAO;

import Beans.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassroomDAO {
    public boolean haveClassroom(Classroom classroom){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from classroom where building=? and roomnumber=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,classroom.getBuilding());
            stmt.setObject(2,classroom.getRoomnumber());
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
