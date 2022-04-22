import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Town> towns = session.createQuery("FROM Town WHERE char_length(name) > 5", Town.class).list();
        for (Town town : towns) {
            session.evict(town);
            town.setName(town.getName().toUpperCase());
            session.update(town);
        }

        session.getTransaction().commit();
        session.close();
    }
}
