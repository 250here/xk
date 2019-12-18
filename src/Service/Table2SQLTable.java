package Service;

import DAO.DBConnections;
import util.Table2SQLTable.Table;
import util.TableCheck;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Table2SQLTable {
    enum datatype{VARCHAR20,INT11};
    public static String importTeacherFromFile(String path){
        String[] teathertableattrs=new String[]{"teacherid","teachername","teacherpassword"};
        try{
            Table table=new Table(path,teathertableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws  Exception{

                }
            };
            return insert(ic,"teacher",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importTakesFromFile(String path){
        String[] teathertableattrs=new String[]{"teacherid","teachername","teacherpassword"};
        try{
            Table table=new Table(path,teathertableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws Exception {
                    TableCheck.checkStudentTime(conn);
                }
            };
            return insert(ic,"takes",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importSectionFromFile(String path){
        String[] sectiontableattrs=new String[]{"courseid","sectionid",
                "building","roomnumber","examtype","timesoltid","studentnamenumber"};
        try{
            Table table=new Table(path,sectiontableattrs);

        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "Section table import succeed.";
    }
//    private static void duplicateDataCheck(Table table){
//        List headers=table.getAttrs();
//        int headernum=headers.size();
//        int length=table.getSize();
//        for(int i=0;i<length;i++){
//
//        }
//    }
    private static String insert(InsertChecker ic,String tablename,Table table,datatype[] types){
        Connection conn= DBConnections.borrowConnection();
        boolean commit=false;
        try{
            conn.setAutoCommit(false);
            int width=table.getAttrs().size();
            int rowsnum=table.getSize();
            assert width==types.length;
            String sql=getSQL(tablename,table);
            for(List<String> row:table.getData()){
                PreparedStatement stat =conn.prepareStatement(sql);
                for(int col=0;col<types.length;col++){
                    switch (types[col]){
                        case INT11:
                            stat.setObject(col,Integer.parseInt(row.get(col)));
                            break;
                        case VARCHAR20:
                            String s=row.get(col);
                            if(s.getBytes().length>20){
                                throw new RuntimeException("Too long:"+s);
                            }
                            stat.setObject(col,s);
                    }
                }
                stat.execute();
            }
            ic.check(conn);
            conn.commit();
            conn.setAutoCommit(true);
            commit=true;
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            return "主键重复："+e.getMessage();
        }catch(java.sql.SQLException e){
            System.out.println(e.getErrorCode());
            e.printStackTrace();
            return "error:"+e.getMessage();
        }catch (RuntimeException e){
            return e.getMessage();
        }catch(Exception e){
            e.printStackTrace();
            return "error:"+e.getMessage();
        }finally {
            try{
                if(!commit){
                  conn.rollback();
                }
                conn.setAutoCommit(true);
            }catch (Exception e){}
        }
        return tablename+" table import succdeed.";
    }
    private static String getSQL(String tablename,Table table){
        StringBuilder s=new StringBuilder("INSERT INTO ");
        s.append(tablename);
        s.append(" (");
        List<String> names=table.getAttrs();
        StringBuilder signs=new StringBuilder();
        for(int i=0;i<names.size();i++){
            s.append(names.get(i));
            signs.append("?");
            if(i!=names.size()){
                s.append(",");
                signs.append(",");
            }
        }
        s.append(") VALUES (");
        s.append(signs);
        s.append(")");
        return s.toString();
    }
}
abstract class InsertChecker{
    abstract void check(Connection conn) throws Exception;
}