package exercise.footballBettingDatabase;

import javax.persistence.*;

@Entity
@Table(name = "bet_games")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.bet",
        joinColumns = @JoinColumn(name = "bet_id")),
        @AssociationOverride(name = "primaryKey.game",
        joinColumns = @JoinColumn(name = "game_id"))
})
public class BetGame {
    @EmbeddedId
    private BetGameId primaryKey = new BetGameId();
    @OneToOne
    @JoinColumn(name = "result_prediction", referencedColumnName = "id")
    private ResultPrediction resultPrediction;
}
