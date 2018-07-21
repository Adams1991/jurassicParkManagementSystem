package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PaddockTest {

    Paddock paddock;
    Park park;
    List<Herbivore> herbivores;
    List<Carnivore> carnivores;
    Carnivore trex;

    @Before
    public void setUp() {
        park = new Park();
        paddock = new Paddock(park, "Paddock One", false);
        trex = new Carnivore("Trextacy",100, SpeciesType.TREX, paddock);
    }

    @Test
    public void getPark() {
        assertEquals(park, paddock.getPark());
    }

    @Test
    public void setPark() {
        paddock.setPark(null);
        assertEquals(null, paddock.getPark());
    }

    @Test
    public void getName() {
        assertEquals("Paddock One", paddock.getName());
    }

    @Test
    public void setName() {
        paddock.setName("Paddock Two");
        assertEquals("Paddock Two", paddock.getName());
    }

    @Test
    public void getCarnivores() {
        assertEquals(null, paddock.getCarnivores());
    }

    @Test
    public void setCarnivores() {
        paddock.setCarnivores(carnivores);
        assertEquals(carnivores, paddock.getCarnivores());
    }

    @Test
    public void getHerbivores() {
        assertEquals(null, paddock.getHerbivores());
    }

    @Test
    public void setHerbivores() {
        paddock.setHerbivores(herbivores);
        assertEquals(herbivores, paddock.getHerbivores());
    }

    @Test
    public void getBooleonForPaddockStatus(){
        assertEquals(false, paddock.isPaddockBroken());
    }

    @Test
    public void canBreakPaddock(){
        trex.starveCarnivore();
        paddock.breakout(trex);
        assertEquals(true, paddock.isPaddockBroken());
    }
}