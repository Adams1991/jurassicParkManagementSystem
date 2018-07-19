package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PaddockTest {

    Paddock paddock;
    Park park;
    private ArrayList<Herbivore> herbivores;
    private ArrayList<Carnivore> carnivores;

    @Before
    public void setUp() {
        park = new Park();
        paddock = new Paddock(park, "Paddock One");
        herbivores = new ArrayList<Herbivore>();
        carnivores = new ArrayList<Carnivore>();
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
}