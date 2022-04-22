package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "squad_number")
    private Integer squadNumber;
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private PlayerPosition playerPosition;
    @Column(name = "is_injured")
    private boolean isInjured;
    @OneToMany(mappedBy = "primaryKey.player")
    private Set<PlayerGame> playerGames;
}
