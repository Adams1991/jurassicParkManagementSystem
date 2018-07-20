package db;

import models.Paddock;
import models.Park;
import models.Visitor;

public class Seeds {
    public static void seedData() {

        DBHelper.deleteAll(Park.class);

        Park park = new Park("Jurassic Park");
        DBHelper.save(park);

        Paddock paddock = new Paddock(park, "Raptor Enclosure");
        DBHelper.save(paddock);

        Visitor visitor = new Visitor("David Pears", 40, 180, park);
        DBHelper.save(visitor);


    }
}
