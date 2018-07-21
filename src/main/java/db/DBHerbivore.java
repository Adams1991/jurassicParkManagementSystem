package db;

import controllers.PaddockController;
import models.Food;
import models.Paddock;
import models.Staff;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBHerbivore {
    private static Transaction transaction;
    private static Session session;

  // not using this code at the moment
    public static List<Paddock> getPaddocksWithoutCarn() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        try {
            Criteria cr = session.createCriteria(Paddock.class);
            results =  cr.list();
            for (Paddock result : results) {
                if (result.getCarnivores().size() != 0)
                {results.remove(result);}
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
}
