package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff extends Person {
    private RoleType job;
    private List<Food> foods;
    private Park park;

    public Staff(){}

    public Staff(String name, RoleType job, Park park) {
        super(name);
        this.job = job;
        this.park = park;
    }
    @Enumerated(value = EnumType.STRING)
    public RoleType getJob() {
        return job;
    }

    public void setJob(RoleType job) {
        this.job = job;
    }
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @ManyToOne
    @JoinColumn(name="park_id", nullable=false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }


//    public int feedDinosaur(FoodType foodType) {
//            return foodType.nutritionalValueForEating();
//        }
//    }


    public int feedDinosaur(FoodType foodType) {
        for (Food food : foods) {
            if (food.getFood() == foodType)
                return foodType.nutritionalValueForEating();
        }
        return 0;
    }

    public int foodsCount() {
        return foods.size();
    }
}

