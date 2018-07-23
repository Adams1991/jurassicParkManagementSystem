package controllers;

import db.DBHelper;
import db.DBPaddock;
import db.DBPark;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

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

            // Get paddocks only with Carns in
            List<Paddock> paddocksWithCarn = park.returnPaddocksWithCarns();

            // Randomly starve dinos in above paddocks
            park.starveDinoInAListofPaddocks(paddocksWithCarn);

            //Get Random Visitor from DB
            List<Visitor> visitors = DBPark.visitorsInPark(park);
            Random rand = new Random();
            int randomVisitorInArray = rand.nextInt(visitors.size())+1;
            Visitor visitor = DBHelper.find(Visitor.class, randomVisitorInArray);

            // Get Random Staff From DB
            List<Staff> staff = DBPark.staffInPark(park);
            Random randStaff = new Random();
            int randomStaffInArray = randStaff.nextInt(staff.size())+(staff.size());
            Staff staffForEating = DBHelper.find(Staff.class, randomStaffInArray );

            // Get Random Carnivore From DB
            List<Carnivore> carnivores = park.returnListOfCarnsinPaddockList(paddocksWithCarn);
            Random randCarn = new Random();
            int randomCarnivore = randCarn.nextInt(carnivores.size())+1;
            Carnivore carnivore = DBHelper.find(Carnivore.class , randomCarnivore);

            //Check if Paddocks broken and eat either a Visitor Or Guest if there are any
            park.eatVisitorIfPaddocksBroken(paddocksWithCarn, visitor, carnivore, staffForEating);

            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

    }
}

