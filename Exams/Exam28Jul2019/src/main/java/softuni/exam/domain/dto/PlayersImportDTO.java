package softuni.exam.domain.dto;

import softuni.exam.domain.entities.Position;

import java.math.BigDecimal;
import java.util.Arrays;

public class PlayersImportDTO {
    private String firstName;
    private String lastName;
    private int number;
    private BigDecimal salary;
    private String position;
    private PictureImportDTO picture;
    private TeamImportDTO team;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PictureImportDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureImportDTO picture) {
        this.picture = picture;
    }

    public TeamImportDTO getTeam() {
        return team;
    }

    public void setTeam(TeamImportDTO team) {
        this.team = team;
    }

    public boolean isValid() {
        if (this.lastName.length() < 3 || this.lastName.length() > 15) {
            return false;
        }
        if (this.number < 1 || this.number > 99) {
            return false;
        }
        if (this.salary.doubleValue() < 0) {
            return false;
        }
        if (Arrays.stream(Position.values()).noneMatch(p -> p.getValue().equals(this.position))) {
            return false;
        }
        return true;
    }
}
