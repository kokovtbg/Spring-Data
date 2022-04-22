import entities.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Address> addressList = session.createQuery(
                "FROM Address AS a ORDER BY a.employees.size DESC", Address.class
        ).setMaxResults(10).list();
        addressList.forEach(a -> System.out.printf("%s, %s - %d employees%n",
                a.getText(), a.getTown().getName(), a.getEmployees().size()));


        session.getTransaction().commit();
        session.close();
    }
}
