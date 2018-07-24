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

        //logout post
        post("/parks/:id/logout", (req, res) -> {
            Map<String, Object> model = new HashMap();
            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);
            model.put("park", park);

            //Gets all paddocks from a park
            List<Paddock> paddockCheckForPark = DBPark.paddockInPark(park);
            model.put("paddocks", paddockCheckForPark);

            //check if park has paddocks going to redirect to funny page
            if(paddockCheckForPark.size() == 0){res.redirect("/error");}


            // Get paddocks only with Carns in
            List<Paddock> paddocksWithCarn = park.returnPaddocksWithCarns();

            //check if paddocks have carns
            if(paddocksWithCarn.size() == 0){res.redirect("/error");}

            // Randomly starve dinos in above paddocks
            park.starveDinoInAListofPaddocks(paddocksWithCarn);

            //Get Random Visitor linked to park from DB and redirect to error page if none
            List<Visitor> visitors = DBPark.visitorsInPark(park);
            if(visitors.size() == 0){res.redirect("/error");}
            model.put("visitors", visitors);
            Random rand = new Random();
            int randomVisitorInArray = rand.nextInt(visitors.size()) + 1;
            Visitor visitor = DBHelper.find(Visitor.class, randomVisitorInArray);

            // Get Random Staff linked to park From DB and redirect to error page if none
            List<Staff> staff = DBPark.staffInPark(park);
            if(staff.size() == 0){res.redirect("/error");}
            model.put("staff", staff);
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

            // Get All people in park
            List<Person> peopleInPark = DBPark.peopleInPark(park);

            //Loops through and changes root dependent on people being eaten
            for (Person person : peopleInPark) {
                if (person.isHasBeenEaten()) {
                    model.put("template", "templates/parks/rampagelogout.vtl");
                    return new ModelAndView(model, "templates/layout.vtl");
                } else {
                    model.put("template", "templates/parks/normallogout.vtl");
                    return new ModelAndView(model, "templates/layout.vtl");
                }
            }
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

            FoodType foodType = FoodType.valueOf(food.toUpperCase());

           List<Food> attractionFoods = DBHelper.getAll(Food.class);

            // loops through staffFoods and deletes one which matches chosen foodtype. breaks
            // so it doesn't loop all the way through when condition is met.
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

           visitor.buyTicketForAttraction(attraction);
           DBHelper.update(visitor);

           park.setAttraction(attraction);
           DBHelper.update(park);

           park.visitorGoesToAttraction(visitor);
           DBHelper.update(attraction);



            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());


// error page if any random produces a null value
       get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/parks/error.vtl");


            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }

}

