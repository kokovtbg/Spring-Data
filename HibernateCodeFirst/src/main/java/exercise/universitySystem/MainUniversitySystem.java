package exercise.universitySystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainUniversitySystem {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("university");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.close();
    }
}
