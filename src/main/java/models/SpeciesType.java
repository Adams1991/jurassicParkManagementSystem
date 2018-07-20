package models;

import javax.persistence.Entity;
import javax.persistence.Table;


public enum SpeciesType {

    TREX(2),
    RAPTOR(100),
    STEGOSAURUS(1),
    TRICEROTOPS(1),
    DIPLODOCUS(5);

    private int nutritionalValue;

    SpeciesType(int nutritionalValue){
        this.nutritionalValue = nutritionalValue;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

}
