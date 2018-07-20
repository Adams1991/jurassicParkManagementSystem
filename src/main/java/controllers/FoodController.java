package controllers;

import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class FoodController {
    public FoodController(){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

        // READ
        get("/foods", (req, res) -> { // get verb, set to /managers route with request & response
            Map<String, Object> model = new HashMap(); // creating new hashmap with string and object key/value
            model.put("template", "templates/foods/index.vtl"); // put

            List<Food> foods = DBHelper.getAll(Food.class);
            model.put("foods", foods);

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());

        // CREATE GET
        get("/foods/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Staff> staffs = DBHelper.getAll(Staff.class);
            model.put("staffs", staffs);

            List<FoodType> cuisines = Arrays.asList(FoodType.values());
            model.put("cuisines", cuisines);

            model.put("template", "templates/foods/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        // CREATE POST
        post("/foods", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String cuisineValue = req.queryParams("cuisine");
            FoodType cuisine = FoodType.valueOf(cuisineValue.toUpperCase());

            int staffId = Integer.parseInt(req.queryParams("staff"));
            Staff staff = DBHelper.find(Staff.class, staffId);

            Food food = new Food(cuisine, staff);
            DBHelper.save(food);

            res.redirect("/foods");
            return null;

        }, new VelocityTemplateEngine());

        // DELETE
        post("/foods/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Food food = DBHelper.find(Food.class, id);
            DBHelper.delete(food);
            res.redirect("/foods");
            return null;
        }, new VelocityTemplateEngine());

        // UPDATE GET
        get("/foods/:id/edit",(req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Staff> staff = DBHelper.getAll(Staff.class);
            int id = Integer.parseInt(req.params(":id"));

            List<FoodType> cuisines = Arrays.asList(FoodType.values());
            model.put("cuisines", cuisines);

            Food food = DBHelper.find(Food.class, id);
            model.put("food", food);
            model.put("staffs", staff);
            model.put("template", "templates/foods/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //UPDATE POST

        post("foods/:id",(req, res) ->{
            Map<String, Object> model = new HashMap<>();

            String cuisineValue = req.queryParams("cuisine");
            FoodType cuisine = FoodType.valueOf(cuisineValue.toUpperCase());

            int staffId = Integer.parseInt(req.queryParams("staff"));
            Staff staff = DBHelper.find(Staff.class, staffId);

            int foodId = Integer.parseInt(req.params(":id"));
            Food food = DBHelper.find(Food.class, foodId);
            food.setFood(cuisine);
            food.setStaff(staff);
            DBHelper.update(food);

            res.redirect("/foods");
            return null;
        }, new VelocityTemplateEngine());

    }
}
