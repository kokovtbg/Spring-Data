package exercise.footballBettingDatabase;

import javax.persistence.*;

@Entity
@Table(name = "colors")
public class KitColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(mappedBy = "primaryKitColor", targetEntity = Team.class)
    private Team teamOne;
    @OneToOne(mappedBy = "secondaryKitColor", targetEntity = Team.class)
    private Team teamTwo;
}
