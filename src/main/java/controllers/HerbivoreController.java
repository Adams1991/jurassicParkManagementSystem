package controllers;

import db.DBHelper;
import models.Herbivore;
import models.Paddock;
import models.SpeciesType;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class HerbivoreController {

    public HerbivoreController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        // READ
        get("/herbivores", (req, res) -> { // get verb, set to /managers route with request & response
            Map<String, Object> model = new HashMap(); // creating new hashmap with string and object key/value
            model.put("template", "templates/herbivores/index.vtl"); // put

            List<Herbivore> herbivores = DBHelper.getAll(Herbivore.class);
            model.put("herbivores", herbivores);

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());

        // CREATE GET
        get("/herbivores/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Paddock> paddocksWithoutCarn = new ArrayList<>();

            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);

            for (Paddock paddock : paddocks) {
                if (paddock.CarnAmount() == 0)
                    paddocksWithoutCarn.add(paddock);
            }

            model.put("paddocks", paddocksWithoutCarn);

            List<SpeciesType> species = Arrays.asList(SpeciesType.values());
            model.put("species", species);

            model.put("template", "templates/herbivores/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        // CREATE POST
        post("/herbivores", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int paddockId = Integer.parseInt(req.queryParams("paddock"));

            Paddock paddock = DBHelper.find(Paddock.class, paddockId);

            String speciesType = req.queryParams("specie");

            SpeciesType speciesTypeEnum= SpeciesType.valueOf(speciesType.toUpperCase());

            String name = req.queryParams("name");

            int hungerLevel = 100;

            Herbivore herbivore = new Herbivore( name, hungerLevel, speciesTypeEnum, paddock);
            DBHelper.save(herbivore);

            res.redirect("/herbivores");
            return null;

//

        }, new VelocityTemplateEngine());

        // DELETE
        post("/herbivores/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Herbivore herbivore = DBHelper.find(Herbivore.class, id);
            DBHelper.delete(herbivore);
            res.redirect("/herbivores");
            return null;
        }, new VelocityTemplateEngine());

        // UPDATE GET
        get("/herbivores/:id/edit",(req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Paddock> paddocksWithoutCarn = new ArrayList<>();

            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);

            for (Paddock paddock : paddocks) {
                if (paddock.CarnAmount() == 0)
                    paddocksWithoutCarn.add(paddock);
            }

            int id = Integer.parseInt(req.params(":id"));
            Herbivore herbivore = DBHelper.find(Herbivore.class, id);
            model.put("herbivore", herbivore);
            model.put("paddocks", paddocksWithoutCarn);
            model.put("template", "templates/herbivores/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//UPDATE POST

        post("herbivores/:id",(req, res) ->{
            Map<String, Object> model = new HashMap<>();


            String name = req.queryParams("name");

            int paddockId = Integer.parseInt(req.queryParams("paddock"));

            Paddock paddock = DBHelper.find(Paddock.class, paddockId);


            int id = Integer.parseInt(req.params(":id"));
            Herbivore herbivore = DBHelper.find(Herbivore.class, id);


            herbivore.setName(name);
            herbivore.setPaddock(paddock);
            DBHelper.update(herbivore);

            res.redirect("/herbivores");
            return null;
        }, new VelocityTemplateEngine());

    }
}
