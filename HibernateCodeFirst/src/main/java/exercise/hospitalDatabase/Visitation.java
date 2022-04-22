package exercise.hospitalDatabase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    @Column(length = 1000)
    private String comments;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @OneToMany(mappedBy = "visitation", targetEntity = Diagnose.class)
    private Set<Diagnose> diagnoses;
}
