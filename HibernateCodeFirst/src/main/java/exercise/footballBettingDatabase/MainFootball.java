package exercise.footballBettingDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainFootball {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("football");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.close();
    }
}
