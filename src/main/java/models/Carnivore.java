package models;

public class Carnivore extends Dinosaur {

    public Carnivore() {
    }

    public Carnivore(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
        super(name, hungerLevel, species, paddock);
    }
}
