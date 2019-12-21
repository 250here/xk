package util.Table2SQLTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {
    private List<List<String>> data=new ArrayList<>();
    private List<String> headers=new ArrayList<>();
    //path:csv file's path
    //headers:the column name that the table must contains
    //IndexOutOfBoundsException,FileNotFoundException
    public Table(String path,String[] headers)throws Exception{
        File file=new File(path);
        try(
                Scanner input=new Scanner(file);
                ){
            String line=input.nextLine();
            List<String> tableheaders=solveLine(line);
            ArrayList<Integer> indexs=new ArrayList<>();
            for(String head:headers){
                if(tableheaders.contains(head)){
                    this.headers.add(head);
                    indexs.add(tableheaders.indexOf(head));
                    data.add(new ArrayList<String>());
                }else{
                    throw new RuntimeException("列名不符,属性名称或数量不正确");
                }
            }
            assert headers.length==indexs.size();
            while (input.hasNextLine()){
                line=input.nextLine();
                List<String> tuple=solveLine(line);
                if(tuple.size()<headers.length){
                    throw new RuntimeException("存在空行或者空字段");
                }
                for(int i=0;i<indexs.size();i++){
                    data.get(i).add(tuple.get(indexs.get(i)));
                }
            }
            int len=data.get(0).size();
            for(List l:data){
                assert len==l.size();
            }
        }
    }
    private List<String> solveLine(String line){
        ArrayList<String> values=new ArrayList<>();
        for(String s:line.split(",")){
            values.add(s);
        }

        return values;
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
