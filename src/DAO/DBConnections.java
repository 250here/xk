package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Vector;

public class DBConnections {
    private static Vector<Connection> connections=new Vector<>();
    private static final String URL="jdbc:mysql://192.168.0.104:3306/xk?useSSL=false&serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="998179Aa.7954";

    {
        if(connections.size()==0){
            while(connections.size()<10){
                createConnection();
            }
        }
    }
    private DBConnections(){
    }
    public static Connection borrowConnection(){
        if(!connections.isEmpty()){
            return connections.remove(0);
        }else{
            return createConnection();
        }
    }

    public static void returnConnection(Connection connection){
        if(connection!=null){
            try{
                if(!connection.isClosed()){
                    connections.add(connection);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static  Connection createConnection() {
        Connection conn=null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获得连接
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void executeSql(String sql,Object...objs) {
        Connection conn= DBConnections.borrowConnection();

        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
            //循环为每一个变量设置参数
            for(int i=0;i<objs.length;i++) {
                ps.setObject(i+1, objs[i]);
            }
            //执行sql
            ps.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try{
                    ps.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            DBConnections.returnConnection(conn);
        }
    }
    public static void executeSql(Connection conn,String sql,Object...objs) {

        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
            //循环为每一个变量设置参数
            for(int i=0;i<objs.length;i++) {
                ps.setObject(i+1, objs[i]);
            }
            //执行sql
            ps.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try{
                    ps.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
