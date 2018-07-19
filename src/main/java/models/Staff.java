package models;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;

public class Staff extends Person {
    private RoleType job;
    private ArrayList<Food> foods;

    public Staff(){}

    public Staff(String name, RoleType job, Park park) {
        super(name, park);
        this.job = job;
    }
    @Enumerated(value = EnumType.STRING)
    public RoleType getJob() {
        return job;
    }

    public void setJob(RoleType job) {
        this.job = job;
    }
    @Column(name = "foods")
    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
