package db;

import models.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import sun.util.resources.cldr.rn.CalendarData_rn_BI;

import java.util.ArrayList;
import java.util.List;

public class DBPark {
    public static Session session;

    public static List<Paddock> paddockInPark(Park park) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        try {
            Criteria cr = session.createCriteria(Paddock.class);
            cr.add(Restrictions.eq("park", park));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


    public static List<Visitor> visitorsInPark(Park park) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Visitor> results = null;
        try {
            Criteria cr = session.createCriteria(Visitor.class);
            cr.add(Restrictions.eq("park", park));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


    public static List<Staff> staffInPark(Park park) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Staff> results = null;
        try {
            Criteria cr = session.createCriteria(Staff.class);
            cr.add(Restrictions.eq("park", park));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

        public static List<Person> peopleInPark(Park park) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> results = null;
        try {
            Criteria cr = session.createCriteria(Person.class);
            cr.add(Restrictions.eq("park", park));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


    public static Attraction getAttractionsinPark(Park park) {
        session = HibernateUtil.getSessionFactory().openSession();
        Attraction attraction = null;
        try {
            Criteria cr = session.createCriteria(Attraction.class);
            cr.add(Restrictions.eq("park", park));
            attraction = (Attraction) cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return attraction;
    }
    
    public static List<Carnivore> carnsInPark(Park park) {
        List<Paddock> paddocks = DBPark.paddockInPark(park);
        List<Carnivore> results = new ArrayList<>();
        for (Paddock paddock : paddocks) {
            results.addAll(DBPaddock.carnivoresInPaddock(paddock));
        }
        return results;
    }


    public static List<Herbivore> herbsInPark(Park park) {
        List<Paddock> paddocks = DBPark.paddockInPark(park);
        List<Herbivore> results = new ArrayList<>();
        for (Paddock paddock : paddocks) {
            results.addAll(DBPaddock.HerbInPaddock(paddock));
        }
        return results;
    }



}
