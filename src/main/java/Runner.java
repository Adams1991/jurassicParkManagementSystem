import db.DBHelper;
import models.*;

import java.util.List;

public class Runner {
    public static void main(String[] args) {



        //CRUD Functions for Park
        Park park = new Park("Jurassic Park");
        DBHelper.save(park);

        Park park2 = new Park("Jurassic Park");
        DBHelper.save(park2);

        park.setName("Disney");
        DBHelper.update(park);

        List<Park> allParks = DBHelper.getAll(Park.class);

        DBHelper.delete(park2);

        List<Park> allParks2 = DBHelper.getAll(Park.class);

        DBHelper.find(Park.class, 1);

        //CRUD Function for Staff

        Staff staffMember = new Staff("Pavel Cody", RoleType.GAMEKEEPER, park);
        DBHelper.save(staffMember);
        staffMember.setJob(RoleType.SECURITYGUARD);
        DBHelper.update(staffMember);

        Staff staffMember2 =  new Staff("Pavel Cody", RoleType.GAMEKEEPER, park);;
        DBHelper.save(staffMember2);

        List<Staff> allStaff = DBHelper.getAll(Staff.class);

        DBHelper.delete(staffMember2);

        List<Staff> allStaff2 = DBHelper.getAll(Staff.class);


        //CRUD Function for Paddock
        Paddock tRexEnclosure = new Paddock(park, "Tyranasaurs");
        DBHelper.save(tRexEnclosure);
        tRexEnclosure.setName("Bla");
        DBHelper.update(tRexEnclosure);


        Paddock grassEateres = new Paddock(park, "Field");
        DBHelper.save(grassEateres);
        DBHelper.delete(grassEateres);

        Carnivore tRex = new Carnivore("Lizard King", 100, SpeciesType.TREX, tRexEnclosure);
        DBHelper.save(tRex);

        Herbivore dippy = new Herbivore("Long Tail", 100, SpeciesType.DIPLODOCUS, tRexEnclosure);
        DBHelper.save(dippy);

        Visitor visitor = new Visitor("Dan", 26, 182, park);
        DBHelper.save(visitor);


    }
}
