package softuni.exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanesRootImportDTO {
    @XmlElement(name = "plane")
    private List<PlaneImportDTO> planes;

    public PlanesRootImportDTO() {
    }

    public List<PlaneImportDTO> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlaneImportDTO> planes) {
        this.planes = planes;
    }
}
