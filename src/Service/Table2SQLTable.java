package Service;

import DAO.DBConnections;
import util.Table2SQLTable.GradeTable;
import util.Table2SQLTable.Table;
import util.Table2SQLTable.TableCheck;
import util.Table2SQLTable.TableImpl;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static String importClassroomFromFile(String path){
        String[] teathertableattrs=new String[]{"building","roomnumber","capacity"};
        try{
            Table table=new Table(path,teathertableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws  Exception{

                }
            };
            return insert(ic,"classroom",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importCourseFromFile(String path){
        String[] teathertableattrs=new String[]{"courseid","title","credits","hour"};
        try{
            Table table=new Table(path,teathertableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws  Exception{
                    TableCheck.checkCourseTitle(conn);
                }
            };
            return insert(ic,"course",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.INT11,datatype.INT11});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importTimeslotFromFile(String path){
        String[] timeslottableattrs=new String[]{"timeslotid","day","starttime","endtime"};
        try{
            Table table=new Table(path,timeslottableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws  Exception{
                    TableCheck.checkTimeslot(conn);
                }
            };
            return insert(ic,"timeslot",table,new datatype[]{datatype.INT11,datatype.INT11,datatype.INT11,datatype.INT11});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importStudentFromFile(String path){
        String[] studenttableattrs=new String[]{"studentid","studentname","studentpassword"};
        try{
            Table table=new Table(path,studenttableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws  Exception{

                }
            };
            return insert(ic,"student",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importGradeFromFile(String path,String courseid,String sectionid){
        String[] teathertableattrs=new String[]{"studentid","grade"};
        try{
            TableImpl table=new GradeTable(courseid,sectionid,path);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws  Exception{

                }
            };
            return insert(ic,"takes",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importTeachesFromFile(String path){
        String[] teathertableattrs=new String[]{"teacherid","courseid","sectionid"};
        try{
            Table table=new Table(path,teathertableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws Exception {
                    TableCheck.checkTeacherTime(conn);
                }
            };
            return insert(ic,"teaches",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importSectionFromFile(String path){
        String[] sectiontableattrs=new String[]{"courseid","sectionid",
                "building","roomnumber","examtype","timeslotid","studentnumberlimit"};
        try{
            Table table=new Table(path,sectiontableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws Exception {
                    TableCheck.checkSectionTime(conn);
                    TableCheck.checkClassroomTime(conn);
                    TableCheck.checkSectionStudentLimitLessThanClassroomcapacity(conn);
                }
            };
            return insert(ic,"section",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,
                    datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20,datatype.INT11,datatype.INT11});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String importExamFromFile(String path){
        String[] examtableattrs=new String[]{"courseid","sectionid",
                "building","roomnumber","examday","examstarttime","examendtime"};
        try{
            Table table=new Table(path,examtableattrs);
            InsertChecker ic=new InsertChecker() {
                @Override
                void check(Connection conn) throws Exception {
//                    TableCheck.checkExamTime(conn);
                    TableCheck.checkExamClassroomTime(conn);
                }
            };
            return insert(ic,"exam",table,new datatype[]{datatype.VARCHAR20,datatype.VARCHAR20,datatype.VARCHAR20,
                datatype.VARCHAR20,datatype.INT11,datatype.INT11,datatype.INT11});
        }catch (FileNotFoundException e){
            return "文件不存在";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
//    private static void duplicateDataCheck(Table table){
//        List headers=table.getAttrs();
//        int headernum=headers.size();
//        int length=table.getSize();
//        for(int i=0;i<length;i++){
//
//        }
//    }
    private static String insert(InsertChecker ic, String tablename, TableImpl table, datatype[] types){
        Connection conn= DBConnections.borrowConnection();
        boolean commit=false;
        try{
            conn.setAutoCommit(false);
            int width=table.getAttrs().size();
            int rowsnum=table.getSize();
            assert width==types.length;
            String sql;
            if(!"takes".equals(tablename)){
                sql=getSQL(tablename,table);
            }else{
                sql=getUpdateGradeSQL();
            }
            for(int rownum=0;rownum<rowsnum;rownum++){
                PreparedStatement stat =conn.prepareStatement(sql);
                for(int col=0;col<types.length;col++){
                    switch (types[col]){
                        case INT11:
                            stat.setObject(col+1,table.getInt(rownum,col));
                            break;
                        case VARCHAR20:
                            String s=table.getString(rownum,col);
                            if(s.getBytes().length>20){
                                throw new RuntimeException("Too long:"+s);
                            }
                            stat.setObject(col+1,s);
                    }
                }

                stat.execute();
            }
            ic.check(conn);
            conn.commit();
            conn.setAutoCommit(true);
            commit=true;
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return "主键重复或外键约束不满足："+e.getMessage();
        }catch(java.sql.SQLException e){
            System.out.println(e.getErrorCode());
            e.printStackTrace();
            return "error:"+e.getMessage();
        }catch (RuntimeException e){
            e.printStackTrace();
            return "runtimeexception"+e.getMessage();
        }catch(Exception e){
            e.printStackTrace();
            return "error:"+e.getMessage();
        }finally {
            try{
                if(!commit){
                  conn.rollback();
                }
                conn.setAutoCommit(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            DBConnections.returnConnection(conn);
        }
        return tablename+" table import succeed.";
    }
    private static String getSQL(String tablename,TableImpl table){
        StringBuilder s=new StringBuilder("INSERT INTO ");
        s.append(tablename);
        s.append(" (");
        List<String> names=table.getAttrs();
        StringBuilder signs=new StringBuilder();
        for(int i=0;i<names.size();i++){
            s.append(names.get(i));
            signs.append("?");
            if(i<names.size()-1){
                s.append(",");
                signs.append(",");
            }
        }
        s.append(") VALUES (");
        s.append(signs);
        s.append(")");
        return s.toString();
    }
    private static String getUpdateGradeSQL(){
        StringBuilder s=new StringBuilder("update takes ");
        s.append("set grade=? ");
        s.append("where ");
        s.append("courseid=? and ");
        s.append("sectionid=? and ");
        s.append("studentid=?");
        return s.toString();
    }
}
abstract class InsertChecker{
    abstract void check(Connection conn) throws Exception;
}