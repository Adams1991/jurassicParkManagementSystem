package controllers;

import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class AttractionController {

    public AttractionController() {
        this.setupEndPoints();
    }

    private void setupEndPoints() {
        //READ

        get("/attraction", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/attractions/index.vtl");

            List<Attraction> attractions = DBHelper.getAll(Attraction.class);
            model.put("attractions", attractions);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        // CREATE GET
        get("/attractions/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Park> parks = DBHelper.getAll(Park.class);
            model.put("parks", parks);

            model.put("template", "templates/attractions/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        // CREATE POST
        post("/attractions", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");

            int parkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(Park.class, parkId);

            Attraction attraction = new Attraction(name, 0 , park);
            DBHelper.save(attraction);

            res.redirect("/attractions");
            return null;

        }, new VelocityTemplateEngine());


        // DELETE
        post("/attractions/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Attraction attraction = DBHelper.find(Attraction.class, id);
            DBHelper.delete(attraction);
            res.redirect("/attractions");
            return null;
        }, new VelocityTemplateEngine());

        // UPDATE GET
        get("/attractions/:id/edit",(req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Park> park = DBHelper.getAll(Park.class);
            int id = Integer.parseInt(req.params(":id"));


            Attraction attraction = DBHelper.find(Attraction.class, id);
            model.put("attraction", attraction);
            model.put("parks", park);
            model.put("template", "templates/attractions/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //UPDATE POST

        post("attractions/:id",(req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");

            int parkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(Park.class, parkId);

            int till = Integer.parseInt(req.queryParams("till"));

            int id = Integer.parseInt(req.params(":id"));
            Attraction attraction = DBHelper.find(Attraction.class,id);
            attraction.setName(name);
            attraction.setTill(till);
            attraction.setPark(park);
            DBHelper.update(attraction);

            res.redirect("/attractions");
            return null;
        }, new VelocityTemplateEngine());

    }
}
