import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.util.Date;

public class ItemDAO {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static Transaction tr = null;

    public static Item save(Item item) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                session.save(item);
            tr.commit();
            System.out.println("Save is done");
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return item;
    }

    public static Item update(Item item){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(item);
            tr.commit();
            System.out.println("Update is done");
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return item;
    }

    public static Item findById(long id){
        Item item=null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                item = (Item) session.get(Item.class,id);
            tr.commit();
            System.out.println("Find By ID is done");
        } catch (HibernateException | NoResultException e) {
            System.err.println("Find By ID is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return item;
    }

    public static void delete(long id){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Item item = (Item) session.get(Item.class,id);
            if (item != null)
                session.delete(item);
            tr.commit();
            System.out.println("Delete is done");
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}


