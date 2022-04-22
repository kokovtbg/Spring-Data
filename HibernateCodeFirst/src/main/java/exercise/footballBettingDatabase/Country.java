package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(length = 3)
    private String id;
    private String name;
    @OneToMany(mappedBy = "country", targetEntity = Town.class)
    private Set<Town> towns;
    @ManyToMany(mappedBy = "countries", targetEntity = Continent.class)
    private Set<Continent> continents;
}
