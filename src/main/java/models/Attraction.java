package models;

import behaviours.ISecurity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attractions")
public class Attraction implements ISecurity {

    private int id;
    private List<Food> foods;
    private int cost;
    private Park park;
    private String name;

    public Attraction() {
    }

    public Attraction(String name, int cost, Park park) {
        this.name = name;
        this.cost = 10;
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

    @OneToMany(mappedBy = "attraction", fetch = FetchType.LAZY)
    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "park_id", nullable = false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public boolean isAllowed(Visitor visitor) {
        if (visitor.getAge() < 18) {
            return false;
        } else {
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
