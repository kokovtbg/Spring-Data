package com.example.football.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(length = 3, nullable = false)
    private String position;
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    @ManyToOne(optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;
    @OneToOne(optional = false)
    @JoinColumn(name = "stat_id", referencedColumnName = "id")
    private Stat stat;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
