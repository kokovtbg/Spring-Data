package exercise.footballBettingDatabase;

import javax.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String prediction;
    @OneToOne(mappedBy = "resultPrediction", targetEntity = BetGame.class)
    private BetGame betGame;
}
