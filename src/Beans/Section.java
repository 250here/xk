package Beans;

public class Section {
    private String courseId;
    private String sectionId;
    private String sectionName;
    private String examType;
    private int studentNumberLimit;
    private int numberOfStudent;
    private String building;
    private String roomNumber;
    private int credits;
    private int timeSlotId;

    public Section(String courseId,String sectionId){
        this.courseId = courseId;
        this.sectionId = sectionId;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getStudentNumberLimit() {
        return studentNumberLimit;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setStudentNumberLimit(int studentNumberLimit) {
        this.studentNumberLimit = studentNumberLimit;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
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

}
