package exercise.billsPaymentSystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", targetEntity = BillingDetail.class)
    private Set<BillingDetail> billingDetails;

}
