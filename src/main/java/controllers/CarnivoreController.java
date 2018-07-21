package controllers;

import db.DBHelper;
import models.Carnivore;
import models.Paddock;
import models.SpeciesType;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.*;

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
            Paddock paddock = DBHelper.findByName(Paddock.class, "Holding Paddock");

            model.put("paddock",  paddock);
            model.put("carnivores", carnivores);

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());

        // CREATE GET
        get("/carnivores/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            Paddock paddock = DBHelper.findByName(Paddock.class, "Holding Paddock");
            model.put("paddock", paddock);


            List<SpeciesType> species = Arrays.asList(SpeciesType.values());
            model.put("species", species);



            model.put("template", "templates/carnivores/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        // CREATE POST
        post("/carnivores", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

//            int paddockId = Integer.parseInt(req.queryParams("paddock"));

            Paddock paddock = DBHelper.findByName(Paddock.class, "Holding Paddock");

            String speciesType = req.queryParams("specie");

            SpeciesType speciesTypeEnum= SpeciesType.valueOf(speciesType.toUpperCase());

            String name = req.queryParams("name");

            int hungerLevel = 100;

            Carnivore carnivore = new Carnivore( name, hungerLevel, speciesTypeEnum, paddock);
            DBHelper.save(carnivore);

            res.redirect("/carnivores");
            return null;

//

        }, new VelocityTemplateEngine());

        // DELETE
        post("/carnivores/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Carnivore carnivore = DBHelper.find(Carnivore.class, id);
            DBHelper.delete(carnivore);
            res.redirect("/carnivores");
            return null;
        }, new VelocityTemplateEngine());

        // UPDATE GET
        get("/carnivores/:id/edit",(req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Paddock> paddocksWithCarn = new ArrayList<>();

            List<Paddock> paddocksWithType = new ArrayList<>();

            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);

            int id = Integer.parseInt(req.params(":id"));

            Carnivore carnivore = DBHelper.find(Carnivore.class, id);

            for (Paddock paddock : paddocks) {
                if (paddock.CarnAmount() != 0)
                    paddocksWithCarn.add(paddock);
            }

            for (Paddock paddockWithCarn : paddocksWithCarn) {
                for (Carnivore carnivoreInPaddock : paddockWithCarn.getCarnivores()) {
                    if (carnivore.getSpecies() == carnivoreInPaddock.getSpecies())
                        paddocksWithType.add(paddockWithCarn);
                }
            }

            // gets rid of duplicates caused by eager loading
            Set<Paddock> hs = new HashSet<Paddock>();
            hs.addAll(paddocksWithType);
            paddocksWithType.clear();
            paddocksWithType.addAll(hs);

            model.put("carnivore", carnivore);
            model.put("paddocks", paddocksWithType);
            model.put("template", "templates/carnivores/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//UPDATE POST

        post("carnivores/:id",(req, res) ->{
            Map<String, Object> model = new HashMap<>();


            String name = req.queryParams("name");

            int paddockId = Integer.parseInt(req.queryParams("paddock"));

            Paddock paddock = DBHelper.find(Paddock.class, paddockId);


            int id = Integer.parseInt(req.params(":id"));
            Carnivore carnivore = DBHelper.find(Carnivore.class, id);


            carnivore.setName(name);
            carnivore.setPaddock(paddock);
            DBHelper.update(carnivore);

            res.redirect("/carnivores");
            return null;
        }, new VelocityTemplateEngine());

    }
}
