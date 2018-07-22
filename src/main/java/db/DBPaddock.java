package db;

import models.Carnivore;
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

}
