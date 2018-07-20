package models;


import behaviours.IEdible;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="dinosaurs")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dinosaur  implements IEdible {
    int id;
    String name;
    int hungerLevel;
    SpeciesType species;
    Paddock paddock;

    public Dinosaur() {
    }

    public Dinosaur(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
        this.name = name;
        this.hungerLevel = hungerLevel;
        this.species = species;
        this.paddock = paddock;
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

    @ManyToOne
    @JoinColumn(name="paddock_id", nullable=false)
    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }

    public int nutritionalValueForEating(){
        return species.getNutritionalValue();
    }

}
