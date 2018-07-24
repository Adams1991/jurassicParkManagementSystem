package models;

import javax.persistence.Entity;
import javax.persistence.Table;


public enum SpeciesType {

    TREX(50),
    RAPTOR(5),
    STEGOSAURUS(50),
    TRICEROTOPS(50),
    DIPLODOCUS(5),
    SPINOSAURAUS(20),
    ARCHAEOPTERYX(5),
    CARCHARODONTOSAURUS(30),
    ARGARGENTINOSAURUS(100),
    BRACHIOSAURUS(80);



    private int nutritionalValue;

    SpeciesType(int nutritionalValue){
        this.nutritionalValue = nutritionalValue;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

}
