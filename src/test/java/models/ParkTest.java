package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParkTest {

    Park park;
    ArrayList staff;
    ArrayList paddocks;
    ArrayList visitors;





    @Before
    public void setUp() {
    park = new Park ("Park One");
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
    }

    @Test
    public void setVisitors() {
    }

    @Test
    public void getPaddocks() {
    }

    @Test
    public void setPaddocks() {
    }
}