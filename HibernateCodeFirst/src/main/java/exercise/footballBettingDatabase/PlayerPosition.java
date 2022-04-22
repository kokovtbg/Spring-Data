package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "positions")
public class PlayerPosition {
    @Id
    @Column(length = 2)
    private String id;
    @Column(name = "position_description")
    private String positionDescription;
    @OneToMany(mappedBy = "playerPosition", targetEntity = Player.class)
    private Set<Player> players;
}
