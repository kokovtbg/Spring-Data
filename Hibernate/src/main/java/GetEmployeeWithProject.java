import entities.Employee;
import entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Comparator;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int employeeID = Integer.parseInt(scanner.nextLine());

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = session.createQuery(
                String.format("FROM Employee WHERE id = %d", employeeID), Employee.class
        ).getSingleResult();
        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("\t%s%n", p.getName()));

        session.getTransaction().commit();
        session.close();
    }
}
