package exercise.hospitalDatabase;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private Blob picture;
    @Column(name = "has_insurance")
    private boolean hasInsurance;
    @OneToMany(mappedBy = "patient", targetEntity = Visitation.class)
    private Set<Visitation> visitations;
}
