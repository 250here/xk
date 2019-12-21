package Beans;

public class Exam {
    private String courseid;
    private String sectionid;
    private String building;
    private String roomnumber;
    private int examday;
    private int examstarttime;
    private int examendtime;

    public Exam(){
    }
    public Exam(String courseid,String sectionid){
        this.courseid = courseid;
        this.sectionid = sectionid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getExamday() {
        return examday;
    }

    public void setExamday(int examday) {
        this.examday = examday;
    }

    public int getExamstarttime() {
        return examstarttime;
    }

    public void setExamstarttime(int examstarttime) {
        this.examstarttime = examstarttime;
    }

    public int getExamendtime() {
        return examendtime;
    }

    public void setExamendtime(int examendtime) {
        this.examendtime = examendtime;
    }
}
