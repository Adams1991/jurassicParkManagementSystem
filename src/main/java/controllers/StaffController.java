package controllers;

import db.DBHelper;
import models.Park;
import models.RoleType;
import models.Staff;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class StaffController {
    public StaffController() {
        this.setupEndPoints();
    }

    public void setupEndPoints(){
        // READ
        get("/staffs", (req, res) -> { // get verb, set to /managers route with request & response
            Map<String, Object> model = new HashMap(); // creating new hashmap with string and object key/value
            model.put("template", "templates/staff/index.vtl"); // put

            List<Staff> staffs = DBHelper.getAll(Staff.class);
            model.put("staffs", staffs);

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());


        // CREATE GET
        get("/staffs/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Park> parks = DBHelper.getAll(Park.class);
            model.put("parks", parks);

            List<RoleType> jobs = Arrays.asList(RoleType.values());
            model.put("jobs", jobs);

            model.put("template", "templates/staff/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        // CREATE POST
        post("/staffs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");

            String roleValue = req.queryParams("job");
            RoleType job = RoleType.valueOf(roleValue.toUpperCase());

            int parkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(Park.class, parkId);

            Staff staff = new Staff(name, job, park);
            DBHelper.save(staff);

            res.redirect("/staffs");
            return null;

        }, new VelocityTemplateEngine());

        // DELETE
        post("/staffs/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Staff staff = DBHelper.find(Staff.class, id);
            DBHelper.delete(staff);
            res.redirect("/staffs");
            return null;
        }, new VelocityTemplateEngine());


        // UPDATE GET
        get("/staffs/:id/edit",(req, res) -> {
            HashMap<String, Object> model = new HashMap();

            List<Park> park = DBHelper.getAll(Park.class);
            int id = Integer.parseInt(req.params(":id"));

            List<RoleType> jobs = Arrays.asList(RoleType.values());
            model.put("jobs", jobs);

            Staff staff = DBHelper.find(Staff.class, id);
            model.put("staff", staff);
            model.put("parks", park);
            model.put("template", "templates/staff/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //UPDATE POST

        post("staffs/:id",(req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");

            String roleValue = req.queryParams("job");
            RoleType job = RoleType.valueOf(roleValue.toUpperCase());

            int parkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(Park.class, parkId);

            int id = Integer.parseInt(req.params(":id"));
            Staff staff = DBHelper.find(Staff.class,id);
            staff.setName(name);
            staff.setJob(job);
            staff.setPark(park);
            DBHelper.update(staff);

            res.redirect("/staffs");
            return null;
        }, new VelocityTemplateEngine());

    }

}
