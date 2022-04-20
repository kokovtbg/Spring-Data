package com.example.football.models.dto;

import com.example.football.models.entity.Town;

public class TeamsImportDTO {
    private String name;
    private String stadiumName;
    private int fanBase;
    private String history;
    private String townName;
    private Town town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public int getFanBase() {
        return fanBase;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public boolean isValid() {
        if (this.name.length() < 3) {
            return false;
        }
        if (this.stadiumName.length() < 3) {
            return false;
        }
        if (this.fanBase < 1000) {
            return false;
        }
        if (this.history.length() < 10) {
            return false;
        }
        return true;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
