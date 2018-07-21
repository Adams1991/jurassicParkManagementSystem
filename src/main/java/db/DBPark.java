package db;

import models.Paddock;
import models.Park;
import models.Visitor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
}
