package com.example.football.models.dto;

public class TownsImportDTO {
    private String name;
    private int population;
    private String travelGuide;

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

    public boolean isValid() {
        if (this.name.length() < 2 ) {
            return false;
        }
        if (this.population < 1) {
            return false;
        }
        if (this.travelGuide.length() < 10) {
            return false;
        }
        return true;
    }
}
