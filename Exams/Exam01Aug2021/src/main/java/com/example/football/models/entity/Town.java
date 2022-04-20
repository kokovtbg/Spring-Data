package com.example.football.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;
    private int population;
    @Column(columnDefinition = "TEXT", name = "travel_guide", nullable = false)
    private String travelGuide;
    @OneToMany(targetEntity = Team.class, mappedBy = "town")
    private Set<Team> teams;
    @OneToMany(targetEntity = Player.class, mappedBy = "town")
    private Set<Player> players;

    public Town() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
