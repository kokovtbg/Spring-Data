package exercise.hospitalDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 1000)
    private String comments;
    @ManyToOne
    @JoinColumn(name = "visitation_id", referencedColumnName = "id")
    private Visitation visitation;
    @OneToMany(mappedBy = "diagnose", targetEntity = Medicament.class)
    private Set<Medicament> medicaments;
}
