package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsRegisterDTO {
    @XmlElement(name = "part")
    private List<PartRegisterDTO> parts;

    public PartsRegisterDTO() {
    }

    public List<PartRegisterDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartRegisterDTO> parts) {
        this.parts = parts;
    }
}
