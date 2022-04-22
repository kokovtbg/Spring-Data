package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Blob logo;
    @Column(length = 3)
    private String initials;
    @OneToOne
    @JoinColumn(name = "primary_kit_color", referencedColumnName = "id")
    private KitColor primaryKitColor;
    @OneToOne
    @JoinColumn(name = "secondary_kit_color", referencedColumnName = "id")
    private KitColor secondaryKitColor;
    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;
    private Double budget;
    @OneToMany(mappedBy = "team", targetEntity = Player.class)
    private Set<Player> players;
    @OneToOne(mappedBy = "homeTeam", targetEntity = Game.class)
    private Game gameOne;
    @OneToOne(mappedBy = "awayTeam", targetEntity = Game.class)
    private Game gameTwo;
}
