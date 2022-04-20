package softuni.exam.models.dto;

public class TownsImportDTO {
    private String townName;
    private int population;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isValid() {
        if (this.townName.length() < 2) {
            return false;
        }
        if (this.population < 1) {
            return false;
        }
        return true;
    }
}
