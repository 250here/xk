package util.Table2SQLTable;

import DAO.DBConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestTable {
    public static void main(String[] args) throws Exception{
//        String[] headers=new String[]{"user","pet","money"};
//        Table table=new Table("./test.csv",headers);
//        for(int i=0;i<table.getSize();i++){
//            for(int j=0;j<2;j++){
//                System.out.println(table.getString(i,j));
//            }
//            System.out.println(table.getInt(i,headers[2]));
//        }
        Connection conn=DBConnections.borrowConnection();
        conn.setAutoCommit(false);
        PreparedStatement stat =conn.prepareStatement("insert into teacher values ('T001','enstein','dd')");
        stat.execute();
        stat =conn.prepareStatement("insert into teacher values ('T001','enstein','dd')");
        stat.execute();
        conn.commit();
        conn.setAutoCommit(true);
    }
    private void println(String s){
        System.out.println(s);
    }
}
