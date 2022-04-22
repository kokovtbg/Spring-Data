package exercise.footballBettingDatabase;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BetGameId implements Serializable {
    @ManyToOne
    private Bet bet;
    @ManyToOne
    private Game game;
}
