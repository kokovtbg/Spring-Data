package com.example.football.models.dto;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDTO {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDateString;
    @XmlTransient
    private LocalDate birthDate;
    @XmlElement
    private String position;
    @XmlElement(name = "town")
    private TownNameImportDTO townName;
    @XmlElement(name = "team")
    private TeamNameImportDTO teamName;
    @XmlElement(name = "stat")
    private StatIdDTO statIdDTO;

    public PlayerImportDTO() {
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

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
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

    public TownNameImportDTO getTownName() {
        return townName;
    }

    public void setTownName(TownNameImportDTO townName) {
        this.townName = townName;
    }

    public TeamNameImportDTO getTeamName() {
        return teamName;
    }

    public void setTeamName(TeamNameImportDTO teamName) {
        this.teamName = teamName;
    }

    public StatIdDTO getStatIdDTO() {
        return statIdDTO;
    }

    public void setStatIdDTO(StatIdDTO statIdDTO) {
        this.statIdDTO = statIdDTO;
    }

    public boolean isValid() {
        if (this.firstName.length() < 3) {
            return false;
        }
        if (this.lastName.length() < 3) {
            return false;
        }
        if (!this.email.contains("@") || !this.email.contains(".")) {
            return false;
        }
        return true;
    }
}
