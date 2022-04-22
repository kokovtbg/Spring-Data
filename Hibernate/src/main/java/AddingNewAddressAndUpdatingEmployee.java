import entities.Address;
import entities.Employee;
import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lastNameEmployee = scanner.nextLine();

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Address address = new Address();
        address.setText("Vitoshka 15");
        Town town = session.createQuery("FROM Town WHERE name = 'Sofia'", Town.class).getSingleResult();
        address.setTown(town);
        session.persist(address);

        Employee employee = session.createQuery(
                String.format("FROM Employee WHERE lastName = '%s'", lastNameEmployee), Employee.class
        ).getSingleResult();
        session.evict(employee);
        employee.setAddress(address);
        session.update(employee);


        session.getTransaction().commit();
        session.close();
    }
}
