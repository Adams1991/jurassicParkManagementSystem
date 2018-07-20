package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "staff")
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
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }


//    public int feedDinosaur(FoodType foodType) {
//            return foodType.nutritionalValueForEating();
//        }
//    }


    public int feedDinosaur(FoodType foodType) {
        for (Food food : foods) {
            if (food.getFood() == foodType)
                return foodType.nutritionalValueForEating();
            DBHelper.delete(food);
        }
        return 0;
    }

    public int foodsCount() {
        return foods.size();
    }
}

