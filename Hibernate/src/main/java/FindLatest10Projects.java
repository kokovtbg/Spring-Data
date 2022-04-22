import entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Project> projects = session.createQuery(
                "FROM Project ORDER BY startDate DESC", Project.class
        ).setMaxResults(10).list();
        projects.stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("Project name: %s\n" +
                        " \tProject Description: %s\n" +
                        " \tProject Start Date:%s\n" +
                        " \tProject End Date: %s\n",
                        p.getName(), p.getDescription(),
                        p.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n")), p.getEndDate()));


        session.getTransaction().commit();
        session.close();
    }
}
