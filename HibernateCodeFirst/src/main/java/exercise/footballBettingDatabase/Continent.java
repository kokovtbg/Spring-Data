package exercise.footballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(name = "countries_continents",
    joinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Set<Country> countries;
}
