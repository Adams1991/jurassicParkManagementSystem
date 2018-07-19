package models;

public enum SpeciesType {

    TREX(2),
    RAPTOR(100),
    STEGOSAURUS(1),
    DIPLODOCUS(5);

    private int nutritionalValue;

    SpeciesType(int nutritionalValue){
        this.nutritionalValue = nutritionalValue;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

}
