import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> employees = session.createQuery(
                "FROM Employee WHERE salary > 50000", Employee.class
        ).list();
        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        session.getTransaction().commit();
        session.close();
    }
}
