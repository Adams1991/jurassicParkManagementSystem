package db;

import models.*;

public class Seeds {
    public static void seedData() {

        DBHelper.deleteAll(Park.class);

        Park park = new Park("Jurassic Park", 1000);
        DBHelper.save(park);



        Paddock paddock3 = new Paddock(park, "TRex Enclosure", false);
        DBHelper.save(paddock3);

        Paddock holdingPaddock = new Paddock(park, "Holding Paddock", false);
        DBHelper.save(holdingPaddock);



        Paddock paddock = new Paddock(park, "Raptor Enclosure", false);
        DBHelper.save(paddock);

        Paddock paddock2 = new Paddock(park, "Herb Enclosure", false);
        DBHelper.save(paddock2);

        Visitor visitor = new Visitor("David Pears", 40, 180, park);
        DBHelper.save(visitor);

        Staff staff = new Staff("Stewart", RoleType.GAMEKEEPER, park);
        DBHelper.save(staff);

        Food food = new Food(FoodType.BADLYBEHAVEDCHILD,staff);
        DBHelper.save(food);
        Carnivore carnivore = new Carnivore("Alpha", 100, SpeciesType.RAPTOR, paddock);
        DBHelper.save(carnivore);

        Carnivore carnivore2 = new Carnivore("Alpha", 100, SpeciesType.TREX, paddock3);
        DBHelper.save(carnivore2);

        Herbivore herbivore = new Herbivore("Beta", 100, SpeciesType.DIPLODOCUS, paddock2);
        DBHelper.save(herbivore);

    }
}
