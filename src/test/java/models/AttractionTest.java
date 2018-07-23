package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AttractionTest {

    Attraction attraction;
    Park park;
    Visitor visitor;

    @Before
    public void setup(){
        park = new Park();
        attraction = new Attraction("Badly Behaved Child Feeding Frenzy",0, park);
        visitor = new Visitor("Freddy", 17, 160, park, false);
    }

    @Test
    public void hasTill(){
        assertEquals(0, attraction.getTill());
    }

    @Test
    public void hasPark(){
        assertEquals(park, attraction.getPark());
    }

    @Test
    public void isAllowedToWatchBadlyBehavedChildGetEaten(){
        assertEquals(false, attraction.isAllowed(visitor));
    }

    @Test
    public void hasName(){
        assertEquals("Badly Behaved Child Feeding Frenzy", attraction.getName());
    }

}