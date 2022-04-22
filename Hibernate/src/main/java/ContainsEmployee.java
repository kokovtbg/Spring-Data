import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputData = scanner.nextLine().split("\\s+");
        String employeeFirstName = inputData[0];
        String employeeLastName = inputData[1];

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> employee = session.createQuery(
                String.format("FROM Employee WHERE firstName = '%s' AND lastName = '%s'",
                        employeeFirstName, employeeLastName), Employee.class).list();
        if (employee.size() != 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        session.getTransaction().commit();
        session.close();
    }
}
