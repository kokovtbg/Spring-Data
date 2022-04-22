package exercise.footballBettingDatabase;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PlayerGameId implements Serializable {
    @ManyToOne
    private Player player;
    @ManyToOne
    private Game game;
}
