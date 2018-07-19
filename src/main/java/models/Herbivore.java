package models;


import behaviours.IEdible;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "herbivores")
public class Herbivore extends Dinosaur {

    public Herbivore() {
    }


    public Herbivore(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
        super(name, hungerLevel, species, paddock);
    }
}
