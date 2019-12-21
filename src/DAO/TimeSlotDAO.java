package DAO;

import Beans.Section;
import Beans.TimeSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TimeSlotDAO {
    public ArrayList<TimeSlot> getTimeSlot(int timeSlotId){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from timeslot where timeslotid=?";
        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,timeSlotId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int day = rs.getInt("day");
                int startTime = rs.getInt("starttime");
                int endtime = rs.getInt("endtime");

                TimeSlot timeSlot = new TimeSlot(timeSlotId);
                timeSlot.setDay(day);
                timeSlot.setStartTime(startTime);
                timeSlot.setEndTime(endtime);

                timeSlots.add(timeSlot);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return timeSlots;
    }
    public void insertTimeSlot(TimeSlot timeSlot){
        Connection conn=DBConnections.borrowConnection();
        String sql="insert into timeslot values (?,?,?,?)";
        DBConnections.executeSql(sql,timeSlot.getTimeSlotId(),timeSlot.getDay(),timeSlot.getStartTime(),timeSlot.getEndTime());
    }
    public boolean haveTimeSlot(TimeSlot timeSlot){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from timeslot where timeslotid=? and day=? and starttime=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,timeSlot.getTimeSlotId());
            stmt.setObject(2,timeSlot.getDay());
            stmt.setObject(3,timeSlot.getStartTime());
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
