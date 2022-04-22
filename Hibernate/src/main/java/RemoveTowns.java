import entities.Address;
import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Address> list = session.createQuery(
                "FROM Address AS a\n" +
                        String.format("WHERE a.town.name = '%s'", townName), Address.class
        ).list();
        for (int i = 0; i < list.size(); i++) {
            session.delete(list.get(i));
        }
        Town town = session.createQuery(String.format("FROM Town WHERE name = '%s'", townName), Town.class).getSingleResult();
        session.delete(town);
        System.out.printf("%d address in %s deleted%n", list.size(), townName);


        session.getTransaction().commit();
        session.close();
    }
}
