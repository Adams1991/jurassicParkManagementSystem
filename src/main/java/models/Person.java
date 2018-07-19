package models;

import behaviours.IEdible;

import javax.persistence.*;

public abstract class Person implements IEdible {
    private int id;
    private String name;
    private int nutritionalValue;
    private Park park;

    public Person(){}

    public Person(String name, Park park) {
        this.name = name;
        this.nutritionalValue = 10;
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
    @ManyToOne
    @JoinColumn(name="park_id", nullable=false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
