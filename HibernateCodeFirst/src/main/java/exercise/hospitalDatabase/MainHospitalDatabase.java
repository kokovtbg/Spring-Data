package exercise.hospitalDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainHospitalDatabase {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.close();
    }
}
