import entities.Department;
import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.List;


public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<BigDecimal> maxSalaries = session.createQuery(
                "SELECT max(salary) FROM Employee AS e " +
                        "GROUP BY e.department.id HAVING max(salary) NOT BETWEEN 30000 AND 70000", BigDecimal.class
        ).list();
        List<Department> departments = session.createQuery(
                "SELECT e.department FROM Employee AS e " +
                        "GROUP BY e.department.id HAVING max(salary) NOT BETWEEN 30000 AND 70000", Department.class
        ).list();
        for (int i = 0; i < maxSalaries.size(); i++) {
            System.out.printf("%s %.2f%n", departments.get(i).getName(), maxSalaries.get(i));
        }


        session.getTransaction().commit();
        session.close();
    }
}
