package models;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class Food {


    private int id;
    private Staff staff;
    private FoodType food;
    private Attraction attraction;

    public Food(){}

    public Food(FoodType food, Staff staff) {
        this.food = food;
        this.staff = staff;
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

    @ManyToOne
    @JoinColumn(name="staff_id", nullable=false)
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Enumerated(value = EnumType.STRING)
    public FoodType getFood() {
        return food;
    }

    public void setFood(FoodType food) {
        this.food = food;
    }


    @ManyToOne
    @JoinColumn(name="attraction_id", nullable=false)
    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
