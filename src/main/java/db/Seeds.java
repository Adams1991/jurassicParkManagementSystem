package db;

import models.Carnivore;
import models.Paddock;
import models.Park;
import models.SpeciesType;

public class Seeds {
    public static void seedData() {

        DBHelper.deleteAll(Park.class);

        Park park = new Park("Jurassic Park");
        DBHelper.save(park);

        Paddock paddock = new Paddock(park, "Raptor Enclosure");
        DBHelper.save(paddock);

        Carnivore carnivore = new Carnivore("Alpha", 100, SpeciesType.TREX, paddock);
        DBHelper.save(carnivore);


    }
}
