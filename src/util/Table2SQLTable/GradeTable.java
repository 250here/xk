package util.Table2SQLTable;

import java.util.ArrayList;
import java.util.List;

public class GradeTable extends Table{
    List<String> headers;//=new String[]{"courseid","sectionid","studentid","grade"};
    private List<List<String>> data;
    public GradeTable(String courseid,String sectionid,String path)throws Exception{
        super(path,new String[]{"studentid","grade"});
        headers=new ArrayList<>();
        headers.add("courseid");
        headers.add("sectionid");
        headers.add("studentid");
        headers.add("grade");
        data=new ArrayList<>();
        ArrayList<String> addheaders=new ArrayList<>();
        addheaders.add(courseid);
        addheaders.add(sectionid);
        for(List<String> studentid_grade:super.getData()){
            List<String> newrow=new ArrayList<>();
            newrow.addAll(addheaders);
            newrow.addAll(studentid_grade);
            data.add(newrow);
        }
    }

    public String getString(int row,int col){
        return data.get(col).get(row);
    }
    public String getString(int row,String head){
        return getString(row,headers.indexOf(head));
    }
    public int getInt(int row,int col){
        return Integer.parseInt(data.get(col).get(row));
    }
    public int getInt(int row, String head){
        return getInt(row,headers.indexOf(head));
    }
    public int getSize(){
        return data.get(0).size();
    }
    public List<String> getAttrs(){
        return headers;
    }

    public List<List<String>> getData() {
        return data;
    }
}
