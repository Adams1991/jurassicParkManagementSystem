package models;

import behaviours.IEdible;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "carnivores")
public class Carnivore extends Dinosaur {
    private Paddock paddock;

    public Carnivore() {
    }

    public Carnivore(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
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
