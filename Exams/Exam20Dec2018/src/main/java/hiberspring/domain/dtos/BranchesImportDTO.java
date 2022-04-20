package hiberspring.domain.dtos;

public class BranchesImportDTO {
    private String name;
    private String town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isValid() {
        if (this.name == null) {
            return false;
        }
        if (this.town == null) {
            return false;
        }
        return true;
    }
}
