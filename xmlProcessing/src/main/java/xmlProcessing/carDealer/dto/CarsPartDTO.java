package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsPartDTO {
    @XmlElement(name = "car")
    private List<CarSerializeDTO> cars;

    public CarsPartDTO() {
    }

    public List<CarSerializeDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarSerializeDTO> cars) {
        this.cars = cars;
    }
}
