package Beans;

public class Course {
    private String courseId;
    private String title;
    private int credis;
    private int hour;

    public Course(String courseId){
        this.courseId=courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredis() {
        return credis;
    }

    public void setCredis(int credis) {
        this.credis = credis;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
