package controllers;

import db.DBHelper;
import models.Carnivore;
import models.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class CarnivoreController {

    public CarnivoreController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        // READ
        get("/carnivores", (req, res) -> { // get verb, set to /managers route with request & response
            Map<String, Object> model = new HashMap(); // creating new hashmap with string and object key/value
            model.put("template", "templates/carnivores/index.vtl"); // put

            List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
            model.put("carnivores", carnivores);

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());

//        // CREATE GET
//        get("/carnivores/new", (req, res) -> {
//            HashMap<String, Object> model = new HashMap();
//
//            List<Park> parks = DBHelper.getAll(Park.class);
//            model.put("parks", parks);
//
//            model.put("template", "templates/carnivores/create.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//
//        }, new VelocityTemplateEngine());

//        // CREATE POST
//        post("/carnivores", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int ParkId = Integer.parseInt(req.queryParams("park"));
//            Park park = DBHelper.find(Park.class, ParkId);
//            String name = req.queryParams("name");
//
//            Carnivore carnivore = new Carnivore(park, name);
//            DBHelper.save(carnivore);
//
//            res.redirect("/carnivores");
//            return null;
//
//        }, new VelocityTemplateEngine());
//
//        // DELETE
//        post("/carnivores/:id/delete", (req, res) -> {
//            int id = Integer.parseInt(req.params(":id"));
//            Carnivore carnivore = DBHelper.find(Carnivore.class, id);
//            DBHelper.delete(carnivore);
//            res.redirect("/carnivores");
//            return null;
//        }, new VelocityTemplateEngine());
//
//        // UPDATE GET
//        get("/carnivores/:id/edit",(req, res) -> {
//            HashMap<String, Object> model = new HashMap();
//
//            List<Park> park = DBHelper.getAll(Park.class);
//            int id = Integer.parseInt(req.params(":id"));
//            Carnivore carnivore = DBHelper.find(Carnivore.class, id);
//            model.put("carnivore", carnivore);
//            model.put("parks", park);
//            model.put("template", "templates/carnivores/edit.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//
//        }, new VelocityTemplateEngine());
//
////UPDATE POST
//
//        post("carnivores/:id",(req, res) ->{
//            Map<String, Object> model = new HashMap<>();
//
//            int parkId = Integer.parseInt(req.queryParams("park"));
//            Park park = DBHelper.find(Park.class, parkId);
//
//            String name = req.queryParams("name");
//
//            int id = Integer.parseInt(req.params(":id"));
//            Carnivore carnivore = DBHelper.find(Carnivore.class, id);
//            carnivore.setName(name);
//
//            carnivore.setPark(park);
//            DBHelper.update(carnivore);
//
//            res.redirect("/carnivores");
//            return null;
//        }, new VelocityTemplateEngine());

    }
}
