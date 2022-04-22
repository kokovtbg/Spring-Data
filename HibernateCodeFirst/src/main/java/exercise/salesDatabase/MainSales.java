package exercise.salesDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainSales {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sales");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.close();
    }
}
