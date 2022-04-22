package exercise.footballBettingDatabase;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.player",
        joinColumns = @JoinColumn(name = "player_id")),
        @AssociationOverride(name = "primaryKey.game",
        joinColumns = @JoinColumn(name = "game_id"))
})
public class PlayerGame {
    @EmbeddedId
    private PlayerGameId primaryKey = new PlayerGameId();
    @Column(name = "scored_goals")
    private Integer scoredGoals;
    @Column(name = "player_assists")
    private Integer playerAssists;
    @Column(name = "played_minutes")
    private Integer playedMinutes;
}
