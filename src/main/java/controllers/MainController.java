package controllers;

import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class MainController {
    public static void main(String[] args) {

        staticFileLocation("/public");

        Seeds.seedData();

        ParkController parkController = new ParkController();
        PaddockController paddockContoller = new PaddockController();
        VisitorController visitorController = new VisitorController();
        StaffController staffController = new StaffController();
        FoodController foodController = new FoodController();
        CarnivoreController carnivoreController = new CarnivoreController();
        HerbivoreController herbivoreController = new HerbivoreController();
        AttractionController attractionController = new AttractionController();

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/home.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);
    }
}
