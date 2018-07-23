package db;

import models.Food;
import models.FoodType;
import models.Staff;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBStaff {
    private static Transaction transaction;
    private static Session session;


    public static List<Food> getFoods(Staff staff) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Food> results = null;
        try {
            Criteria cr = session.createCriteria(Food.class);
            cr.add(Restrictions.eq("staff", staff));
            results =  cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

//    public static Food getFoods(FoodType foodType) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        Food results = null;
//        try {
//            Criteria cr = session.createCriteria(Food.class);
//            cr.add(Restrictions.eq("", staff));
//            results =  cr.list
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return results;
//    }
//
//
//    public static Course getCourseFromLesson(Lesson lesson){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Course course = null;
//
//        try{
//            Criteria cr = session.createCriteria(Course.class);
//            cr.createAlias("lessons", "lesson");
//            cr.add(Restrictions.eq("lesson.id", lesson.getId()));
//            course = (Course) cr.uniqueResult();
//        }
//        catch (HibernateException ex){
//            ex.printStackTrace();
//        }
//        finally {
//            session.close();
//        }
//        return course;
//    }




}
