package models;

import java.util.ArrayList;

public class Park {
    private int id;
    private String name;
    private ArrayList<Staff> staff;
    private ArrayList<Visitor> visitors;
    private ArrayList<Paddock> paddocks;

    public Park() {
    }

    public Park(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<Visitor> visitors) {
        this.visitors = visitors;
    }

    public ArrayList<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(ArrayList<Paddock> paddocks) {
        this.paddocks = paddocks;
    }
}
