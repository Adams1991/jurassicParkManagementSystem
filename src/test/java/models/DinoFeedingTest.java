package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DinoFeedingTest {

    DinoFeeding dinoFeeding;
    Park park;

    @Before
    public void setup(){
        park = new Park();
        dinoFeeding = new DinoFeeding(0, park);
    }

    @Test
    public void hasTill(){
        assertEquals(0, dinoFeeding.getTill());
    }

    @Test
    public void hasPark(){
        assertEquals(park, dinoFeeding.getPark());
    }

}