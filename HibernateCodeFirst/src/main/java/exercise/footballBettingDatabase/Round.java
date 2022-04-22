package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "round", targetEntity = Game.class)
    private Set<Game> games;
}
