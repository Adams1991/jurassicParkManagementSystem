package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParkTest {

    Park park;




    @Before
    public void setUp() {
    park = new Park ("Park One");

    }

    @Test
    public void getName() {
        assertEquals("Park One", park.getName());
    }

    @Test
    public void setName() {
    }

    @Test
    public void getStaff() {
    }

    @Test
    public void setStaff() {
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