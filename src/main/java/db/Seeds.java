package db;

import models.*;

public class Seeds {
    public static void seedData() {

        DBHelper.deleteAll(Park.class);

        Park park = new Park("Jurassic Park", 1000);
        DBHelper.save(park);

        Paddock paddock = new Paddock(park, "Raptor Enclosure");
        DBHelper.save(paddock);

        Visitor visitor = new Visitor("David Pears", 40, 180, park);
        DBHelper.save(visitor);

        Staff staff = new Staff("Stewart", RoleType.GAMEKEEPER, park);
        DBHelper.save(staff);

        Food food = new Food(FoodType.BADLYBEHAVEDCHILD,staff);
        DBHelper.save(food);
        Carnivore carnivore = new Carnivore("Alpha", 100, SpeciesType.TREX, paddock);
        DBHelper.save(carnivore);

        Herbivore herbivore = new Herbivore("Beta", 100, SpeciesType.DIPLODOCUS, paddock);
        DBHelper.save(herbivore);

    }
}
