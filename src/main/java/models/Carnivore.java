package models;

import behaviours.IEdible;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "carnivores")
public class Carnivore extends Dinosaur {

    public Carnivore() {
    }

    public Carnivore(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
        super(name, hungerLevel, species, paddock);
    }
}
