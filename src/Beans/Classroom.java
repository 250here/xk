package Beans;

public class Classroom {
    private String building;
    private String roomnumber;
    private int capacity;

    public Classroom(){}
    public Classroom(String building,String roomnumber){
        this.building=building;
        this.roomnumber=roomnumber;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
