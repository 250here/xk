package util;

public class Encode {
    public static String parseToUTF8(String string) throws Exception{
        String s = new String(string.getBytes("iso-8859-1"),"utf-8");
        return s;
    }
}
