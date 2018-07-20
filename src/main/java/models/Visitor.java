package models;

import javax.persistence.*;

@Entity
@Table(name = "visitors")
public class Visitor extends Person {
    private int age;
    private int height;
    private Park park;

    public Visitor() {
    }

    public Visitor(String name, int age, int height,Park park) {
        super(name);
        this.age = age;
        this.height = height;
        this.park = park;
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
}
