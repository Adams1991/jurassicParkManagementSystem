package models;

import behaviours.IEdible;

public enum FoodType implements IEdible {

    Chicken(2),
    Cow(100),
    Goat(25),
    BadlyBehavedChild(5);

    private int nutritionalValue;

    FoodType(int nutritionalValue){
        this.nutritionalValue = nutritionalValue;
    }

    public int nutritionalValueForEating(){
        return this.nutritionalValue;
    }

}
