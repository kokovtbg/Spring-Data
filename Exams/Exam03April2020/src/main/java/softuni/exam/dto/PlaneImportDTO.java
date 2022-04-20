package softuni.exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneImportDTO {
    @XmlElement(name = "register-number")
    private String registerNumber;
    @XmlElement
    private int capacity;
    @XmlElement
    private String airline;

    public PlaneImportDTO() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public boolean isValid() {
        if (this.registerNumber.length() < 5) {
            return false;
        }
        if (this.capacity < 1) {
            return false;
        }
        if (this.airline.length() < 2) {
            return false;
        }
        return true;
    }
}
