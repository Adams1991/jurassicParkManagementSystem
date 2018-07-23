package controllers;

import db.DBHelper;
import models.DinoFeeding;
import models.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class DinoFeedingController {

    public DinoFeedingController() {
        this.setupEndPoints();
    }
    private void setupEndPoints(){
            //READ

            get("/attraction", (req, res) -> {
                Map<String, Object> model = new HashMap();
                model.put("template", "templates/attraction/index.vtl");

                List<DinoFeeding> attractions = DBHelper.getAll(Park.class);
                model.put("attractions", attractions);

                return new ModelAndView(model, "templates/layout.vtl");

            }, new VelocityTemplateEngine());




        }
}
