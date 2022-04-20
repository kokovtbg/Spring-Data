package softuni.exam.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "picture")
    private PictureImportDTO picture;

    public TeamImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureImportDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureImportDTO picture) {
        this.picture = picture;
    }

    public boolean isValid() {
        if (this.name.length() < 3 || this.name.length() > 20) {
            return false;
        }
        return true;
    }
}
