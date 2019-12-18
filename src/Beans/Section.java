package Beans;

public class Section {
    private String courseId;
    private String sectionId = null;
    private String exanType;
    private int studentNumberLimit;
    private int numberOfStudent;
    private String building;
    private String roomNumber;
    private int timeSlotId;

    public Section(String courseId,String sectionId){
        this.courseId = courseId;
        this.sectionId = sectionId;
    }

    public int getStudentNumberLimit() {
        return studentNumberLimit;
    }

    public void setStudentNumberLimit(int studentNumberLimit) {
        this.studentNumberLimit = studentNumberLimit;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getExanType() {
        return exanType;
    }

    public void setExanType(String exanType) {
        this.exanType = exanType;
    }


    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }
}
