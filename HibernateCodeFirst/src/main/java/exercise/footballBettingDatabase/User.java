package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    @Column(name = "full_name")
    private String fullName;
    private Double balance;
    @OneToMany(mappedBy = "user", targetEntity = Bet.class)
    private Set<Bet> bets;
}
