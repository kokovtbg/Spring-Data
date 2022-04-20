package hiberspring.domain.dtos;

public class TownsImportDTO {
    private String name;
    private Integer population;

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

    public boolean isValid() {
        if (this.population == null) {
            return false;
        }
        if (this.name == null) {
            return false;
        }
        return true;
    }
}
