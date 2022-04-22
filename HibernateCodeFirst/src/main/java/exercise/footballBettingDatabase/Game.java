package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "home_team", referencedColumnName = "id")
    private Team homeTeam;
    @OneToOne
    @JoinColumn(name = "away_team", referencedColumnName = "id")
    private Team awayTeam;
    @Column(name = "home_team_goals")
    private Integer homeGoals;
    @Column(name = "away_team_goals")
    private Integer awayGoals;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "home_team_win_bet_rate")
    private Double homeTeamWinBetRate;
    @Column(name = "away_team_win_bet_rate")
    private Double awayTeamWinBetRate;
    @Column(name = "draw_bet_rate")
    private Double drawBetRate;
    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;
    @OneToMany(mappedBy = "primaryKey.game")
    private Set<PlayerGame> playerGames;
    @OneToMany(mappedBy = "primaryKey.game")
    private Set<BetGame> betGames;
}
