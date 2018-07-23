package db;

import models.Carnivore;
import models.Herbivore;
import models.Paddock;
import models.Park;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBPaddock {
    public static Session session;

    public static List<Carnivore> carnivoresInPaddock(Paddock paddock) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Carnivore> results = null;
        try {
            Criteria cr = session.createCriteria(Carnivore.class);
            cr.add(Restrictions.eq("paddock", paddock));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Herbivore> HerbInPaddock(Paddock paddock) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Herbivore> results = null;
        try {
            Criteria cr = session.createCriteria(Herbivore.class);
            cr.add(Restrictions.eq("paddock", paddock));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


    public static List<Paddock> paddocksWithNoHerb() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        try {
            Criteria cr = session.createCriteria(Paddock.class);
            cr.add(Restrictions.isEmpty("herbivores"));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Paddock> emptyPaddocks() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        try {
            Criteria cr = session.createCriteria(Paddock.class);
            cr.add(Restrictions.isEmpty("herbivores"));
            cr.add(Restrictions.isEmpty("carnivores"));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }



}
