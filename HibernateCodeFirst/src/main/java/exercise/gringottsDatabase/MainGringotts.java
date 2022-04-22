package exercise.gringottsDatabase;

import javax.persistence.*;

public class MainGringotts {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("gringotts");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.close();
    }
}
