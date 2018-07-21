package controllers;

import db.DBHelper;
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

//        //TAKE A BREAK GET
//        get("/parks/:id/logout", (req, res) -> {
//
//            Map<String, Object> model = new HashMap();
//            model.put("template", "templates/parks/logout.vtl");
//
//            List<Park> parks = DBHelper.getAll(Park.class);
//            model.put("parks", parks);
//
//            return new ModelAndView(model, "templates/layout.vtl");
//
//        }, new VelocityTemplateEngine());
        
        //TAKE A BREAK 
        post("/parks/:id/logout", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();


            int id = parseInt(req.params(":id"));
            Park park = DBHelper.find(Park.class, id);
            
            List<Paddock> paddocks = DBPark.paddockInPark(park);
            
            List<Paddock> paddocksWithCarn = new ArrayList<>();
            for (Paddock paddock : paddocks) {
                if (paddock.CarnAmount() != 0)
                    paddocksWithCarn.add(paddock);
            }

            for (Paddock paddock : paddocksWithCarn) {
                for (Carnivore carnivore : paddock.getCarnivores()) {
                    carnivore.starveCarnivore();
                    DBHelper.update(carnivore);
                    paddock.breakout(carnivore);
                    DBHelper.update(paddock);
                }
            }

            Visitor visitor  = new Visitor();
            List<Visitor> visitors = DBPark.visitorsInPark(park);
            Random rand = new Random();
            int randomVisitorInArray = rand.nextInt(visitors.size())+1;

            for (Paddock paddock : paddocksWithCarn) {
                if (paddock.isPaddockBroken())
                visitor = visitors.get(randomVisitorInArray);
                DBHelper.update(park);
            }

            Carnivore carnivore = new Carnivore();
            List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
            Random randCarn = new Random();
            int randomCarnivoreInArray = rand.nextInt(carnivores.size())+1;

            for (Paddock paddock : paddocksWithCarn) {
                if (paddock.isPaddockBroken())
                    carnivore = carnivores.get(randomCarnivoreInArray);
                    DBHelper.update(paddock);
            }
            
            int visitorMeat = carnivore.kill(visitor);
            carnivore.eat(visitorMeat);
            DBHelper.update(carnivore);
           
            DBHelper.delete(visitor);

            DBHelper.update(park);

            

            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

    }
}

