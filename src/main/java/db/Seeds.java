package db;

import models.*;

public class Seeds {
    public static void seedData() {

        DBHelper.deleteAll(Park.class);

        Park park = new Park("Jurassic Park", 00);
        DBHelper.save(park);

        Attraction attraction = new Attraction("Watch A Kid Get Eaten", 0, park);
        DBHelper.save(attraction);

        Paddock trexPaddock = new Paddock(park, "Tyrannosaurus Rex Enclosure", false);
        DBHelper.save(trexPaddock);

        Paddock holdingPaddock = new Paddock(park, "Holding Paddock", false);
        DBHelper.save(holdingPaddock);

        Paddock carchPaddock = new Paddock(park, "Carcharodontosaurus Enclosure", false);
        DBHelper.save(carchPaddock);

        Paddock archPaddock = new Paddock(park, "Archaeopteryx Enclosure", false);
        DBHelper.save(archPaddock);

        Paddock raptorPaddock = new Paddock(park, "Raptor Enclosure", false);
        DBHelper.save(raptorPaddock);

        Paddock spinePaddock = new Paddock(park, "Spinosaurus Enclosure", false);
        DBHelper.save(spinePaddock);


        Paddock paddock2 = new Paddock(park, "Herb Enclosure", false);
        DBHelper.save(paddock2);

        Visitor visitor = new Visitor("David Pears", 40, 180, park, false);
        DBHelper.save(visitor);

        Visitor visitor1 = new Visitor("James Amos", 28, 185, park, false);
        DBHelper.save(visitor1);

        Visitor visitor2 = new Visitor("Jesus", 2018, 185, park, false);
        DBHelper.save(visitor2);

        Visitor visitor3 = new Visitor("Lee Burgess", 34, 185, park, false);
        DBHelper.save(visitor3);

        Visitor visitor4 = new Visitor("Ed Kinley", 35, 185, park, false);
        DBHelper.save(visitor4);

//        Visitor visitor6 = new Visitor("Can Toraman", 29, 185, park, false);
//        DBHelper.save(visitor6);
//
//        Visitor visitor7 = new Visitor("Jesus", 2018, 189, park, false);
//        DBHelper.save(visitor7);
//
//        Visitor visitor8 = new Visitor("Gary Muir", 27, 182, park, false);
//        DBHelper.save(visitor8);
//
//        Visitor visitor9 = new Visitor("Magda Kisala", 28, 179, park, false);
//        DBHelper.save(visitor9);
//
//        Visitor visitor10 = new Visitor("Helen O'shea", 28, 175, park, false);
//        DBHelper.save(visitor10);
//
//        Visitor visitor11 = new Visitor("Ben Robinson", 24, 180, park, false);
//        DBHelper.save(visitor11);
//
//        Visitor visitor12 = new Visitor("Gregor Cox", 23, 190, park, false);
//        DBHelper.save(visitor12);
//
//        Visitor visitor13 = new Visitor("Will Cornish", 28, 195, park, false);
//        DBHelper.save(visitor13);
//
//
//        Visitor visitor14 = new Visitor("Iona MacBeth", 27, 185, park, false);
//        DBHelper.save(visitor14);
//
//
//        Visitor visitor15 = new Visitor("Stephen Rooney", 28, 185, park, false);
//        DBHelper.save(visitor15);
//
//
//        Visitor visitor16 = new Visitor("Chris Craig", 28, 185, park, false);
//        DBHelper.save(visitor16);
//
//
//        Visitor visitor17 = new Visitor("Stewart Charters", 28, 185, park, false);
//        DBHelper.save(visitor17);
//
        Staff staff = new Staff("Zsolt Something", RoleType.GAMEKEEPER, park, false);
        DBHelper.save(staff);

        Staff staff1 = new Staff("Tony Something", RoleType.GAMEKEEPER, park, false);
        DBHelper.save(staff1);

        Staff staff2 = new Staff("John Harper", RoleType.GAMEKEEPER, park, false);
        DBHelper.save(staff2);

        Staff staff3 = new Staff("Jarrod Bennie", RoleType.GAMEKEEPER, park, false);
        DBHelper.save(staff3);

        Food food = new Food(FoodType.BadlyBehavedChild,staff);
        DBHelper.save(food);

        Food food2 = new Food(FoodType.Chicken,staff1);
        DBHelper.save(food2);

        Food food3 = new Food(FoodType.Cow,staff2);
        DBHelper.save(food3);

        Food food4 = new Food(FoodType.Goat,staff3);
        DBHelper.save(food4);

        Carnivore carnivore = new Carnivore("Alpha the TRex", 0, SpeciesType.TREX, trexPaddock);
        DBHelper.save(carnivore);

        Carnivore carnivore2 = new Carnivore("Spiney the Spinosaurus", 0, SpeciesType.SPINOSAURAUS, spinePaddock);
        DBHelper.save(carnivore2);

        Carnivore carnivore3 = new Carnivore("Claws the Velociraptor", 0, SpeciesType.RAPTOR, raptorPaddock);

        DBHelper.save(carnivore3);

        Carnivore carnivore4 = new Carnivore("Archy the Archaeopteryx", 0, SpeciesType.ARCHAEOPTERYX, archPaddock);
        DBHelper.save(carnivore4);

        Carnivore carnivore5 = new Carnivore(" Sharp Tooth the Carcharodontosaurus", 0, SpeciesType.CARCHARODONTOSAURUS, carchPaddock);
        DBHelper.save(carnivore5);

        Herbivore herbivore = new Herbivore("Argi the Argargentinosaurus", 0, SpeciesType.ARGARGENTINOSAURUS, paddock2);
        DBHelper.save(herbivore);

        Herbivore herbivore1 = new Herbivore("Bach the Brachiosaurus", 0, SpeciesType.BRACHIOSAURUS, paddock2);
        DBHelper.save(herbivore1);

        Herbivore herbivore2 = new Herbivore("Steggy the Stegosaurus", 0, SpeciesType.STEGOSAURUS, paddock2);
        DBHelper.save(herbivore2);

        Herbivore herbivore3 = new Herbivore("Triple the Triceratops", 0, SpeciesType.TRICEROTOPS, paddock2);
        DBHelper.save(herbivore3);

        Herbivore herbivore4 = new Herbivore("Club the Ankylosaurus", 0, SpeciesType.ANKYLOSAURUS, paddock2);
        DBHelper.save(herbivore4);

    }
}