import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class IncreaseSalaries {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<String> paramList = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");
        List<Employee> employees = session.createQuery(
                "FROM Employee AS e WHERE e.department.name IN (:params)", Employee.class
        ).setParameterList("params", paramList).list();
        for (Employee employee : employees) {
            session.evict(employee);
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
            session.update(employee);
        }
        employees.forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary()));

        session.getTransaction().commit();
        session.close();
    }
}
