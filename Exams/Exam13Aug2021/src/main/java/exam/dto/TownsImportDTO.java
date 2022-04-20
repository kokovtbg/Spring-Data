package exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsImportDTO {
    @XmlElement(name = "town")
    private List<TownImportDTO> towns;

    public TownsImportDTO() {
    }

    public List<TownImportDTO> getTowns() {
        return towns;
    }

    public void setTowns(List<TownImportDTO> towns) {
        this.towns = towns;
    }
}
