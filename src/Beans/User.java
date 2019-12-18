package Beans;

public class User {
    public final static String ROOT_START="root";
    public final static String TEACHER_START="T";
    public final static String STUDENT_START="S";
    public String id=null;
    public String name;
    private String pass;
    public User(String id,String name,String password){
        this.id=id;
        this.name=name;
        this.pass = password;
    }
    public User(String id){
        this.id=id;
    }
    public boolean isRoot(){
        return id.startsWith(ROOT_START);
    }
    public boolean isTeacher(){
        return id.startsWith(TEACHER_START);
    }
    public boolean isStudent(){
        return id.startsWith(STUDENT_START);
    }
    public boolean passwordCorrect(String password){
        return pass.equals(password);
    }
}
