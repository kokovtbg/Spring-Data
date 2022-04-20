package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportDTO {
    @XmlElement
    private float passing;
    @XmlElement
    private float shooting;
    @XmlElement
    private float endurance;

    public StatImportDTO() {
    }

    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    public boolean isValid() {
        if (this.passing < 1) {
            return false;
        }
        if (this.shooting < 1) {
            return false;
        }
        if (this.endurance < 1) {
            return false;
        }
        return true;
    }
}
