package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarnivoreTest {

    Carnivore carnivore;
    Paddock paddock;


    @Before
    public void setUp(){
        paddock = new Paddock();
        carnivore = new Carnivore("Bob",  100, SpeciesType.RAPTOR, paddock );
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


}