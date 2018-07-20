package models;


import behaviours.IEdible;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "herbivores")
public class Herbivore extends Dinosaur {
    private Paddock paddock;

    public Herbivore() {
    }


    public Herbivore(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
        super(name, hungerLevel, species);
        this.paddock = paddock;
    }

    @ManyToOne
    @JoinColumn(name="paddock_id", nullable=false)
    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }


}
