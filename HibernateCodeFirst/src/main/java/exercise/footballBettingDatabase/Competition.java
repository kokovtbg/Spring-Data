package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "competition", targetEntity = Game.class)
    private Set<Game> games;
    @ManyToOne
    @JoinColumn(name = "competition_type", referencedColumnName = "id")
    private CompetitionType competitionType;
}
