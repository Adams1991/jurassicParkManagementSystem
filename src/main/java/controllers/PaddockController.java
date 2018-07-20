package controllers;

import db.DBHelper;
import models.Paddock;
import models.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class PaddockController {

    public PaddockController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        // READ
        get("/paddocks", (req, res) -> { // get verb, set to /managers route with request & response
            Map<String, Object> model = new HashMap(); // creating new hashmap with string and object key/value
            model.put("template", "templates/paddocks/index.vtl"); // put

            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("paddocks", paddocks);

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());

        // CREATE GET
        get("/paddocks/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Park> parks = DBHelper.getAll(Park.class);
            model.put("parks", parks);

            model.put("template", "templates/paddocks/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        // CREATE POST
        post("/paddocks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int ParkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(Park.class, ParkId);
            String name = req.queryParams("name");

            Paddock paddock = new Paddock(park, name);
            DBHelper.save(paddock);

            res.redirect("/paddocks");
            return null;

        }, new VelocityTemplateEngine());

        // DELETE
        post("/paddocks/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, id);
            DBHelper.delete(paddock);
            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());

        // UPDATE GET
        get("/paddocks/:id/edit",(req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Park> park = DBHelper.getAll(Park.class);
            int id = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, id);
            model.put("paddock", paddock);
            model.put("parks", park);
            model.put("template", "templates/paddocks/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//UPDATE POST

        post("paddocks/:id",(req, res) ->{
            Map<String, Object> model = new HashMap<>();

            int parkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(Park.class, parkId);

            String name = req.queryParams("name");

            int id = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, id);
            paddock.setName(name);

            paddock.setPark(park);
            DBHelper.update(paddock);

            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());

    }
}
