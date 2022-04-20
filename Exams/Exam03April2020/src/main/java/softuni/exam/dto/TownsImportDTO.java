package softuni.exam.dto;

public class TownsImportDTO {
    private String name;
    private int population;
    private String guide;

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

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public boolean isValid() {
        if (this.name.length() < 2) {
            return false;
        }
        if (this.population < 1) {
            return false;
        }
        return true;
    }
}
