package models;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "visitors")
public class Visitor extends Person {
    private int age;
    private int height;
    private Park park;
    private int wallet;

    public Visitor() {
    }

    public Visitor(String name, int age, int height,Park park, boolean hasBeenEaten) {
        super(name, hasBeenEaten);
        this.age = age;
        this.height = height;
        this.park = park;
        Random rand = new Random();
        this.wallet = rand.nextInt(100)+50;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @ManyToOne
    @JoinColumn(name="park_id", nullable=false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    @Column(name = "wallet")
    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void buyTicketForAttraction(Attraction attraction){
        setWallet(this.wallet -= attraction.getCost());
    }
}
