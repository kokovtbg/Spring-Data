package exercise.salesDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
    private Set<Sale> sales;
}
