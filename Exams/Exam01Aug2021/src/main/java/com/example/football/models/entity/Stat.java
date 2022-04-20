package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "stats")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float shooting;
    private float passing;
    private float endurance;
    @OneToOne(targetEntity = Player.class, mappedBy = "stat")
    private Player player;

    public Stat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayers(Player player) {
        this.player = player;
    }
}
