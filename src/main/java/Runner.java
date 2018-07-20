import db.DBHelper;
import models.*;

public class Runner {
    public static void main(String[] args) {

        DBHelper.deleteAll(Park.class);
        DBHelper.deleteAll(Staff.class);
        DBHelper.deleteAll(Paddock.class);
        DBHelper.deleteAll(Carnivore.class);
        DBHelper.deleteAll(Herbivore.class);
        DBHelper.deleteAll(Visitor.class);

        Park park = new Park("Jurassic Park");
        DBHelper.save(park);

        Staff staffMember = new Staff("Pavel Cody", RoleType.GAMEKEEPER, park);
        DBHelper.save(staffMember);

        Paddock tRexEnclosure = new Paddock(park, "Tyranasaurs");
        DBHelper.save(tRexEnclosure);

        Paddock grassEateres = new Paddock(park, "Field");
        DBHelper.save(grassEateres);

        Carnivore tRex = new Carnivore("Lizard King", 100, SpeciesType.TREX, tRexEnclosure);
        DBHelper.save(tRex);

        Herbivore dippy = new Herbivore("Long Tail", 100, SpeciesType.DIPLODOCUS, grassEateres);
        DBHelper.save(dippy);

        Visitor visitor = new Visitor("Dan", 26, 182, park);
        DBHelper.save(visitor);
    }
}
