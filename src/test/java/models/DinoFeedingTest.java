package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DinoFeedingTest {

    DinoFeeding dinoFeeding;
    Park park;
    Visitor visitor;

    @Before
    public void setup(){
        park = new Park();
        dinoFeeding = new DinoFeeding("Badly Behaved Child Feeding Frenzy",0, park);
        visitor = new Visitor("Freddy", 17, 160, park, false);
    }

    @Test
    public void hasTill(){
        assertEquals(0, dinoFeeding.getTill());
    }

    @Test
    public void hasPark(){
        assertEquals(park, dinoFeeding.getPark());
    }

    @Test
    public void isAllowedToWatchBadlyBehavedChildGetEaten(){
        assertEquals(false, dinoFeeding.isAllowed(visitor));
    }

    @Test
    public void hasName(){
        assertEquals("Badly Behaved Child Feeding Frenzy", dinoFeeding.getName());
    }

}