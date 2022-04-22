import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> employees = session.createQuery(
                "FROM Employee WHERE firstName LIKE '" + pattern + "%'", Employee.class
        ).list();
        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));


        session.getTransaction().commit();
        session.close();
    }
}
