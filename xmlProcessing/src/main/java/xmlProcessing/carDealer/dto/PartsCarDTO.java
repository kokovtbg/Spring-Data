package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsCarDTO {
    @XmlElement(name = "part")
    private List<PartCarDTO> parts;

    public PartsCarDTO() {
    }

    public List<PartCarDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartCarDTO> parts) {
        this.parts = parts;
    }
}
