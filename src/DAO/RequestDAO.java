package DAO;

import Beans.Request;
import Beans.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RequestDAO {
    public ArrayList<Request> getRequestByStudentId(String studentid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from request natural join course where studentid=?";
        ArrayList<Request> requests = new ArrayList<>();

        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,studentid);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                String courseid = rs.getString("courseid");
                String sectionid = rs.getString("sectionid");
                String sectionName = rs.getString("title");
                String state = rs.getString("state");
                String message = rs.getString("message");
System.out.println(sectionName);
                Request request = new Request();
                request.setSectionName(sectionName);
                request.setCourseId(courseid);
                request.setSectionId(sectionid);
                request.setStudentId(studentid);
                request.setState(state);
                request.setMessage(message);

                requests.add(request);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    return requests;
    }
    public ArrayList<Request> getRequestBySectionId(String courseid,String sectionid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from request natural join course where courseid=? and sectionid=?";
        ArrayList<Request> requests = new ArrayList<>();

        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionid);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                String studentid = rs.getString("studentid");
                String sectionName = rs.getString("title");
                String state = rs.getString("state");
                String message = rs.getString("message");

                Request request = new Request();

                request.setSectionName(sectionName);
                request.setCourseId(courseid);
                request.setSectionId(sectionid);
                request.setStudentId(studentid);
                request.setState(state);
                request.setMessage(message);

                requests.add(request);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return requests;
    }
    public ArrayList<Request> getRequestByteacherId(String teacherid){
        ArrayList<Request> requests = new ArrayList<>();
        TeachesDAO teachesDAO = new TeachesDAO();
        ArrayList<Section>sections = teachesDAO.getSectionByTeacherid(teacherid);
        for(Section section:sections){
            for(Request request:getRequestBySectionId(section.getCourseId(),section.getSectionId())){
                requests.add(request);
            }
        }
        return requests;
    }
    public void updateState(Request request,String state){
        Connection conn=DBConnections.borrowConnection();
        String sql="update request set state=? WHERE courseid=? and sectionid=? and studentid=?";
        DBConnections.executeSql(sql,state,request.getCourseId(),request.getSectionId(),request.getStudentId());
    }
    public void insertRequest(Request request){
        Connection conn=DBConnections.borrowConnection();
        String sql="insert into request values (?,?,?,?,?)";
        DBConnections.executeSql(sql,request.getCourseId(),request.getSectionId(),request.getStudentId(),request.getState(),request.getMessage());
    }
    public void deleteRequest(Request request){
        Connection conn=DBConnections.borrowConnection();
        String sql="delete from request where courseid=? and sectionid=? and studentid=?";
        DBConnections.executeSql(sql,request.getCourseId(),request.getSectionId(),request.getStudentId());

    }
    public boolean haveRequest(Request request){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from request where courseid=? and sectionid=? and studentid=?";
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,request.getCourseId());
            stmt.setObject(2,request.getSectionId());
            stmt.setObject(3,request.getStudentId());
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
    public Request getOneRequest(String courseid,String sectionid,String studentid){
        Connection conn=DBConnections.borrowConnection();
        String sql="select * from request where courseid=? and sectionid=? and studentid=?";
        Request request = new Request();
        try{
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setObject(1,courseid);
            stmt.setObject(2,sectionid);
            stmt.setObject(3,studentid);
            ResultSet rs=    stmt.executeQuery();
            if(rs.next()){
                String state = rs.getString("state");
                String message = rs.getString("message");

                request.setCourseId(courseid);
                request.setSectionId(sectionid);
                request.setStudentId(sectionid);
                request.setState(state);
                request.setMessage(message);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBConnections.returnConnection(conn);
        return request;
            }

}
