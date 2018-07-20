package controllers;

import db.DBHelper;
import models.Park;
import models.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class VisitorController {
//    public VisitorController() {
//        this.setupEndPoints();
//    }
//
//    private void setupEndPoints(){
//
//        // READ
//        get("/visitors", (req, res) -> { // get verb, set to /managers route with request & response
//            Map<String, Object> model = new HashMap(); // creating new hashmap with string and object key/value
//            model.put("template", "templates/visitors/index.vtl"); // put
//
//            List<Visitor> visitors = DBHelper.getAll(Visitor.class);
//            model.put("visitors", visitors);
//
//            return new ModelAndView(model,"templates/layout.vtl");
//
//
//        }, new VelocityTemplateEngine());
//
//
//        // CREATE GET
//        get("/visitors/new", (req, res) -> {
//            HashMap<String, Object> model = new HashMap();
//
//            List<Park> parks = DBHelper.getAll(Park.class);
//            model.put("parks", parks);
//
//            model.put("template", "templates/visitors/create.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//
//        }, new VelocityTemplateEngine());
//
//        // CREATE POST
//        post("/visitors", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String name = req.queryParams("name");
//            int age = Integer.parseInt(req.queryParams("age"));
//            int height = Integer.parseInt(req.queryParams("height"));
//            int ParkId = Integer.parseInt(req.queryParams("park"));
//            Park park = DBHelper.find(Park.class, ParkId);
//
//            Visitor visitor = new Visitor(name, age, height, park);
//            DBHelper.save(visitor);
//
//            res.redirect("/visitors");
//            return null;
//
//        }, new VelocityTemplateEngine());
//
//        // DELETE
//        post("/visitors/:id/delete", (req, res) -> {
//            int id = Integer.parseInt(req.params(":id"));
//            Visitor visitor = DBHelper.find(Visitor.class, id);
//            DBHelper.delete(visitor);
//            res.redirect("/visitors");
//            return null;
//        }, new VelocityTemplateEngine());
//
//        // UPDATE GET
//        get("/visitors/:id/edit",(req, res) -> {
//            HashMap<String, Object> model = new HashMap();
//
//            List<Park> parks = DBHelper.getAll(Park.class);
//            int id = Integer.parseInt(req.params(":id"));
//            Manager manager = DBHelper.find(id, Manager.class);
//            model.put("manager", manager);
//            model.put("departments", parks);
//            model.put("template", "templates/managers/edit.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//
//        }, new VelocityTemplateEngine());
//
//
//
//    }

}
