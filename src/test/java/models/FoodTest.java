package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTest {
    Food food;
    Staff bob;
    Park jurassicPark;

    @Before
    public void setup(){
        jurassicPark = new Park();
        bob = new Staff("Bob Smith", RoleType.GAMEKEEPER, jurassicPark, false);
        food = new Food(FoodType.COW, bob);
    }

    @Test
    public void getStaff() {
        assertEquals(bob, food.getStaff());
    }

    @Test
    public void getFood() {
        assertEquals(FoodType.COW, food.getFood());
    }

    @Test
    public void getNutritionalValue(){
        assertEquals(100, food.getFood().nutritionalValueForEating());
    }
}