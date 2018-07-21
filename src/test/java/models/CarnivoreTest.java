package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CarnivoreTest {

    Carnivore carnivore;
    Paddock paddock;

    @Before
    public void setUp(){
        carnivore = new Carnivore("Bob",  100, SpeciesType.RAPTOR, paddock );
        paddock = new Paddock();
    }

    @Test
    public void getName() {
        assertEquals("Bob", carnivore.getName());
    }

    @Test
    public void setName() {
        carnivore.setName("Bill");
        assertEquals("Bill", carnivore.getName());
    }

    @Test
    public void getHungerLevel() {
        assertEquals(100, carnivore.getHungerLevel());
    }

    @Test
    public void setHungerLevel() {
        carnivore.setHungerLevel(300);
        assertEquals(300, carnivore.getHungerLevel());
    }

    @Test
    public void getSpecies() {
        assertEquals(SpeciesType.RAPTOR, carnivore.getSpecies());
    }

    @Test
    public void setSpecies() {
        carnivore.setSpecies(SpeciesType.DIPLODOCUS);
        assertEquals(SpeciesType.DIPLODOCUS, carnivore.getSpecies());
    }

    @Test
    public void getPaddock(){
        assertEquals(paddock, carnivore.getPaddock());

    }

    @Test
    public void setPaddock(){
        carnivore.setPaddock(null);
        assertEquals(null, carnivore.getPaddock());
    }

    @Test
    public void feedDinosaur(){
        carnivore.eat(5);
        assertEquals(105, carnivore.getHungerLevel());
    }


    @Test
    public void isThereACarnivoreInPaddock(){

    }



}