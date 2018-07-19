package models;

import java.util.ArrayList;

public class Paddock {
    int id;
    Park park;
    String name;
    ArrayList<Carnivore> carnivores;
    ArrayList<Herbivore> herbivores;

    public Paddock(Park park, String name) {
        this.park = park;
        this.name = name;
    }

    public Paddock() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Carnivore> getCarnivores() {
        return carnivores;
    }

    public void setCarnivores(ArrayList<Carnivore> carnivores) {
        this.carnivores = carnivores;
    }

    public ArrayList<Herbivore> getHerbivores() {
        return herbivores;
    }

    public void setHerbivores(ArrayList<Herbivore> herbivores) {
        this.herbivores = herbivores;
    }
}
