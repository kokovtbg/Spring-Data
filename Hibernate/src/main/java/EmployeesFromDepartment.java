import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> employees = session.createQuery(
                "FROM Employee AS e WHERE e.department.name = 'Research and Development' " +
                        "ORDER BY salary, id", Employee.class
        ).list();
        for (Employee employee : employees) {
            System.out.printf("%s %s from Research and Development - $%.2f%n",
                    employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        session.getTransaction().commit();
        session.close();
    }
}
