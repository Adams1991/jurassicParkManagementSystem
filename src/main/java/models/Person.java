package models;

import behaviours.IEdible;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements IEdible {
    private int id;
    private String name;
    private int nutritionalValue;
    private boolean hasBeenEaten;

    public Person(){}

    public Person(String name, boolean hasBeenEaten) {
        this.name = name;
        this.nutritionalValue = 10;
        this.hasBeenEaten = false;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "nutritional_value")
    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(int nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public int nutritionalValueForEating(){
        return this.nutritionalValue;
    }

    @Column(name = "has_been_eaten")
    public boolean isHasBeenEaten() {
        return hasBeenEaten;
    }

    public void setHasBeenEaten(boolean hasBeenEaten) {
        this.hasBeenEaten = hasBeenEaten;
    }
}
