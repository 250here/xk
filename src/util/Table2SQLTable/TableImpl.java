package util.Table2SQLTable;

import java.util.List;

public abstract class TableImpl {

    public abstract String getString(int row,int col);
    public abstract String getString(int row,String head);
    public abstract int getInt(int row,int col);
    public abstract int getInt(int row, String head);
    public abstract int getSize();
    public abstract List<String> getAttrs();

}
