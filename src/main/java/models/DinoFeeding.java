package models;

import behaviours.ISecurity;

import javax.persistence.*;
import java.util.List;

public class DinoFeeding implements ISecurity{

    private int id;
    private List<Food> foods;
    private int till;
    private Park park;

    public DinoFeeding(){}

    public DinoFeeding(int till, Park park) {
        this.till = 0;
        this.park = park;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "dino_feeding", fetch = FetchType.LAZY)
    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Column(name = "till")
    public int getTill() {
        return till;
    }

    public void setTill(int till) {
        this.till = till;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "park_id", nullable = false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public boolean isAllowed(Visitor visitor) {
        if(visitor.getAge() < 18) {
            return false;
        }else{
            return true;
        }
    }

    public int feedDinosaurInArraction(FoodType foodType) {
        for (Food food : foods) {
            if (food.getFood() == foodType)
                return foodType.nutritionalValueForEating();
        }
        return 0;
    }
}
