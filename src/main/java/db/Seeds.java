package db;

import models.Park;

public class Seeds {
    public static void seedData() {

        DBHelper.deleteAll(Park.class);

        Park park = new Park("Jurassic Park");


    }
}
