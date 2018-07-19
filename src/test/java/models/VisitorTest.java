package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisitorTest {

    Visitor visitor;
    Park jurassicPark;

    @Before
    public void setup(){
        jurassicPark = new Park();
        visitor = new Visitor("Daniel Warren", 26, 182, jurassicPark);
    }

    @Test
    public void getName(){
        assertEquals("Daniel Warren", visitor.getName());
    }

    @Test
    public void getAge() {
        assertEquals(26, visitor.getAge());
    }

    @Test
    public void getHeight() {
        assertEquals(182, visitor.getHeight());
    }

    @Test
    public void hasNutritionalValue(){
        assertEquals(10, visitor.getNutritionalValue());
    }

    @Test
    public void hasPark(){
        assertEquals(jurassicPark, visitor.getPark());
    }
}