package exercise.billsPaymentSystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MainBillsPaymentSystem {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bills");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.close();
    }
}
