package models;

public class Herbivore extends Dinosaur {

    public Herbivore() {
    }

    public Herbivore(String name, int hungerLevel, SpeciesType species, Paddock paddock) {
        super(name, hungerLevel, species, paddock);
    }


}
