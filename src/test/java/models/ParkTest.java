package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParkTest {

    Park park;
    Staff staffMember;
    Paddock tRexPen;
    Carnivore tRex;
    ArrayList staff;
    ArrayList paddocks;
    ArrayList visitors;
    ArrayList<Carnivore> tyranasaurs;


    @Before
    public void setUp() {
        staffMember = new Staff("Bob Geldolf", RoleType.GAMEKEEPER, park);
        tRexPen = new Paddock(park, "TRex Enclosure");
        park = new Park("Park One",1000);
        paddocks = new ArrayList<Paddock>();
        visitors = new ArrayList<Visitor>();
        staff = new ArrayList<Staff>();
    }

    @Test
    public void getName() {
        assertEquals("Park One", park.getName());
    }

    @Test
    public void setName() {
        park.setName("Park Two");
        assertEquals("Park Two", park.getName());
    }

    @Test
    public void getStaff() {
        assertEquals(null, park.getStaff());
    }

    @Test
    public void setStaff() {
        park.setStaff(staff);
        assertEquals(staff, park.getStaff());
    }

    @Test
    public void getVisitors() {
        assertEquals(null, park.getVisitors());
    }

    @Test
    public void setVisitors() {
        park.setVisitors(visitors);
        assertEquals(visitors, park.getVisitors());
    }

    @Test
    public void getPaddocks() {
        assertEquals(null, park.getPaddocks());
    }

    @Test
    public void setPaddocks() {
        park.setPaddocks(paddocks);
        assertEquals(paddocks, park.getPaddocks());
    }

}