package models;

import behaviours.IEdible;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Random;


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
    @JoinColumn(name = "paddock_id", nullable = false)
    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }

    public void starveCarnivore() {
        Random rand = new Random();
        int starveAmount = rand.nextInt(50);
        setHungerLevel(hungerLevel -= starveAmount);
    }

    public int kill(IEdible iEdible) {
        return iEdible.nutritionalValueForEating();
    }
}