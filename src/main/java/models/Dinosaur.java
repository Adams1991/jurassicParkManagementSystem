package models;


import behaviours.IEdible;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="dinosaurs")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dinosaur  implements IEdible {
    int id;
    String name;
    int hungerLevel;
    SpeciesType species;


    public Dinosaur() {
    }

    public Dinosaur(String name, int hungerLevel, SpeciesType species) {
        this.name = name;
        this.hungerLevel = hungerLevel;
        this.species = species;
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

    @Column(name = "hunger_level")
    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    @Enumerated(value = EnumType.STRING)
    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }


    public int nutritionalValueForEating(){
        return species.getNutritionalValue();
    }

    public void eat(int nutritionalValue){
        this.hungerLevel += nutritionalValue;
    }



}
