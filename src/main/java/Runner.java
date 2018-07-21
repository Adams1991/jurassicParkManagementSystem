import db.DBHelper;
import db.DBHerbivore;
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

        Park foundPark = DBHelper.find(Park.class, 1);

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

        //CRUD Function For Visitor

        Visitor visitorMember = new Visitor("Pavel Cody", 10,182, park);
        DBHelper.save(visitorMember);
        visitorMember.setHeight(192);
        DBHelper.update(visitorMember);

        Visitor visitorMember2 =  new Visitor("Pavel Cody", 10,182, park);;
        DBHelper.save(visitorMember2);

        List<Visitor> allVisitor = DBHelper.getAll(Visitor.class);

        DBHelper.delete(visitorMember2);

        List<Visitor> allVisitor2 = DBHelper.getAll(Visitor.class);

        List<Person> allFolk = DBHelper.getAll(Person.class);

        //CRUD Function for Paddock
        Paddock tRexEnclosure = new Paddock(park, "Tyranasaurs");
        DBHelper.save(tRexEnclosure);
        tRexEnclosure.setName("Bla");
        DBHelper.update(tRexEnclosure);

        Paddock grassEateres = new Paddock(park, "Field");
        DBHelper.save(grassEateres);

        List<Paddock> allPaddock = DBHelper.getAll(Paddock.class);

        DBHelper.delete(grassEateres);

        List<Paddock> allPaddock2 = DBHelper.getAll(Paddock.class);
        
        //CRUD Function for Dinosaur

        Carnivore tRex = new Carnivore("Lizard King", 100, SpeciesType.TREX, tRexEnclosure);
        DBHelper.save(tRex);
        tRex.setSpecies(SpeciesType.RAPTOR);
        DBHelper.update(tRex);

        Herbivore dippy = new Herbivore("Long Tail", 100, SpeciesType.DIPLODOCUS, tRexEnclosure);
        DBHelper.save(dippy);

        List<Dinosaur> allDino = DBHelper.getAll(Dinosaur.class);

        DBHelper.delete(dippy);

        List<Carnivore> allCarn = DBHelper.getAll(Carnivore.class);
        List<Herbivore> allHerb = DBHelper.getAll(Herbivore.class);


        List<Paddock> paddocksWithNoCarns = DBHerbivore.getPaddocksWithoutCarn();





    }
}
