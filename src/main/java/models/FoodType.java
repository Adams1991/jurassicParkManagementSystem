package models;

import behaviours.IEdible;

public enum FoodType implements IEdible {

    CHICKEN(2),
    COW(100),
    HAY(1),
    BADLYBEHAVEDCHILD(5);

    private int nutritionalValue;

    FoodType(int nutritionalValue){
        this.nutritionalValue = nutritionalValue;
    }

    public int nutritionalValueForEating(){
        return this.nutritionalValue;
    }

}
