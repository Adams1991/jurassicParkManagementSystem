package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HerbivoreTest {

    Herbivore herbivore;
    Paddock paddock;


    @Before
    public void setUp(){
        paddock = new Paddock();
        herbivore = new Herbivore("Bob",  100, SpeciesType.RAPTOR, paddock );
    }

    @Test
    public void getName() {
        assertEquals("Bob", herbivore.getName());
    }

    @Test
    public void setName() {
        herbivore.setName("Bill");
        assertEquals("Bill", herbivore.getName());
    }

    @Test
    public void getHungerLevel() {
        assertEquals(100, herbivore.getHungerLevel());
    }

    @Test
    public void setHungerLevel() {
        herbivore.setHungerLevel(300);
        assertEquals(300, herbivore.getHungerLevel());
    }

    @Test
    public void getSpecies() {
        assertEquals(SpeciesType.RAPTOR, herbivore.getSpecies());
    }

    @Test
    public void setSpecies() {
        herbivore.setSpecies(SpeciesType.DIPLODOCUS);
        assertEquals(SpeciesType.DIPLODOCUS, herbivore.getSpecies());
    }

    @Test
    public void getPaddock(){
        assertEquals(paddock, herbivore.getPaddock());

    }

    @Test
    public void setPaddock(){
        herbivore.setPaddock(null);
        assertEquals(null, herbivore.getPaddock());
    }

}