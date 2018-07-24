package controllers;

import db.*;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import sun.security.x509.DNSName;

import java.util.*;

import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;

public class ParkController {
    public ParkController() {
        this.setupEndPoints();
    }

    private void setupEndPoints() {


        //Read

        get("/parks", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/parks/index.vtl");

            List<Park> parks = DBHelper.getAll(Park.class);
            model.put("parks", parks);


            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //CREATE GET

        get("/parks/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            model.put("template", "templates/parks/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //CREATE POST

        post("/parks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int till = parseInt(req.queryParams("till"));
            Park park = new Park(name, till);
            DBHelper.save(park);

            res.redirect("/parks");
            return null;

        }, new VelocityTemplateEngine());

        // DELETE
        post("/parks/:id/delete", (req, res) -> {
            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);
            DBHelper.delete(park);
            res.redirect("/parks");
            return null;
        }, new VelocityTemplateEngine());

        //UPDATE GET
        get("/parks/:id/edit", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);
            model.put("park", park);
            model.put("template", "templates/parks/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //UPDATE POST
        post("parks/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int till = parseInt(req.queryParams("till"));

            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);
            park.setName(name);
            park.setTill(till);
            DBHelper.update(park);

            res.redirect("/parks");
            return null;
        }, new VelocityTemplateEngine());

        //TAKE A BREAK 
        post("/parks/:id/logout", (req, res) -> {

            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);

            //Checks thereas
            List<Paddock> paddockCheckForPark = DBPark.paddockInPark(park);

            //check if park has paddocks goign to redirect to funny page
            if(paddockCheckForPark.size() == 0){res.redirect("/");}


            // Get paddocks only with Carns in
            List<Paddock> paddocksWithCarn = park.returnPaddocksWithCarns();

            //check if paddocks have carns
            if(paddocksWithCarn.size() == 0){res.redirect("/");}

            // Randomly starve dinos in above paddocks
            park.starveDinoInAListofPaddocks(paddocksWithCarn);

            //Get Random Visitor linked to park from DB
            List<Visitor> visitors = DBPark.visitorsInPark(park);
            Random rand = new Random();
            int randomVisitorInArray = rand.nextInt(visitors.size()) + 1;
            Visitor visitor = DBHelper.find(Visitor.class, randomVisitorInArray);

            // Get Random Staff linked to park From DB
            List<Staff> staff = DBPark.staffInPark(park);
            Random randStaff = new Random();
            int randomStaffInArray = randStaff.nextInt(staff.size()) + (staff.size());
            Staff staffForEating = DBHelper.find(Staff.class, randomStaffInArray);

            // Get Random Carnivore linked to park From DB
            List<Carnivore> carnivores = park.returnListOfCarnsinPaddockList(paddocksWithCarn);
            Random randCarn = new Random();
            int randomCarnivore = randCarn.nextInt(carnivores.size()) + 1;
            Carnivore carnivore = DBHelper.find(Carnivore.class, randomCarnivore);

            //Check if Paddocks broken and eat either a Visitor Or Guest if there are any
            park.eatVisitorIfPaddocksBroken(paddocksWithCarn, visitor, carnivore, staffForEating);


            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

//        WATCH ATTRACTION (Get)

        get("/parks/:id/attraction", (req, res) -> {
            Map<String, Object> model = new HashMap();
            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);
            model.put("park", park);

            List<Visitor> visitors= park.showVisitorsWhoAreOver18();
            model.put("visitors", visitors);


            List<Food> attractionFoods = DBHelper.getAll(Food.class);

            model.put("attractionFoods", attractionFoods);

            List<Paddock> paddocksWithCarn = park.returnPaddocksWithCarns();
            List<Carnivore> carnivores = park.returnListOfCarnsinPaddockList(paddocksWithCarn);
            model.put("carnivores", carnivores);

            model.put("template", "templates/parks/attraction.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

      post(":id/attraction",(req, res) ->{

            String food = req.queryParams("attractionFoods");

            FoodType foodType = FoodType.valueOf(food);

           List<Food> attractionFoods = DBHelper.getAll(Food.class);

            // loops through staffFoods and deletes one which matchs chosen foodtype. Return null
            // so it doesn't loop all the way through.
            for (Food foodForDeletion : attractionFoods) {
                if (foodForDeletion.getFood() == foodType)
                DBHelper.delete(foodForDeletion);
                break;
            }


            int dinoId = Integer.parseInt(req.queryParams("carnivore"));
            Carnivore carnivore = DBHelper.find(Carnivore.class, dinoId);

            int foodValue = carnivore.kill(foodType);

            carnivore.eat(foodValue);

            DBHelper.update(carnivore);

            int visitorId = Integer.parseInt(req.queryParams("visitor"));
            Visitor visitor= DBHelper.find(Visitor.class,visitorId);

           int parkId = parseInt(req.params(":id"));
           Park park = DBHelper.find(Park.class, parkId);
           Attraction attraction = DBPark.getAttractionsinPark(park);

           park.setAttraction(attraction);
           DBHelper.update(park);

           park.visitorGoesToAttraction(visitor);
           DBHelper.update(attraction);


            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());
    }

}

