package util.Table2SQLTable;

import java.util.ArrayList;
import java.util.List;

public class GradeTable extends TableImpl{
    List<String> headers;//=new String[]{"courseid","sectionid","studentid","grade"};
    private List<List<String>> data;
    public GradeTable(String courseid,String sectionid,String path)throws Exception{
        Table table=new Table(path,new String[]{"studentid","grade"});
        headers=new ArrayList<>();
        headers.add("grade");
        headers.add("courseid");
        headers.add("sectionid");
        headers.add("studentid");
        data=new ArrayList<>();
        ArrayList<String> addheaders=new ArrayList<>();
        addheaders.add(courseid);
        addheaders.add(sectionid);
        List<String> studentids=new ArrayList<>();
        for(int rown=0;rown<table.getSize();rown++){
            ArrayList<String> studentid_grade=new ArrayList<>();
            studentid_grade.add(table.getString(rown,0));
            studentid_grade.add(table.getString(rown,1));
            if(!studentids.contains(studentid_grade.get(0))){
                studentids.add(studentid_grade.get(0));
            }else {
                throw new RuntimeException("重复studentid:"+studentid_grade.get(0));
            }
            List<String> newrow=new ArrayList<>();
            newrow.add(studentid_grade.get(1));
            newrow.addAll(addheaders);
            newrow.add(studentid_grade.get(0));
            data.add(newrow);
        }
    }

    public String getString(int row,int col){
        return data.get(row).get(col);
    }
    public String getString(int row,String head){
        return getString(row,headers.indexOf(head));
    }
    public int getInt(int row,int col){
        return Integer.parseInt(data.get(row).get(col));
    }
    public int getInt(int row, String head){
        return getInt(row,headers.indexOf(head));
    }
    public int getSize(){
        return data.size();
    }
    public List<String> getAttrs(){
        return headers;
    }

}
