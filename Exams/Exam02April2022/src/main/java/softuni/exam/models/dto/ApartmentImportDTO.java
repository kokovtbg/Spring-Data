package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportDTO {
    @XmlElement
    private String apartmentType;
    @XmlElement
    private double area;
    @XmlElement
    private String town;

    public ApartmentImportDTO() {
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isValid() {
        if (this.area < 40) {
            return false;
        }
        return true;
    }
}
