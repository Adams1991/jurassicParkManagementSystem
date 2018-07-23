package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StaffTest {

    Staff staff;
    Park jurassicPark;
    Food food;
    ArrayList<Food> foods;

    @Before
    public void setup() {
        jurassicPark = new Park();
        foods = new ArrayList<Food>();
        staff = new Staff("Jesus of Nazareth", RoleType.GAMEKEEPER, jurassicPark, false);
        food = new Food(FoodType.BADLYBEHAVEDCHILD, staff);
    }

    @Test
    public void getName() {
        assertEquals("Jesus of Nazareth", staff.getName());
    }

    @Test
    public void hasJob() {
        assertEquals(RoleType.GAMEKEEPER, staff.getJob());
    }

    @Test
    public void hasFood() {
        assertEquals(null, staff.getFoods());
    }

    @Test
    public void hasNutritionalValue() {
        assertEquals(10, staff.getNutritionalValue());
    }

    @Test
    public void hasPark() {
        assertEquals(jurassicPark, staff.getPark());
    }

    @Test
    public void canFeedDinosaurFromFoodEnum(){
        foods.add(food);
        foods.add(food);
        staff.setFoods(foods);
        assertEquals(5, staff.feedDinosaur(FoodType.BADLYBEHAVEDCHILD));
    }
}