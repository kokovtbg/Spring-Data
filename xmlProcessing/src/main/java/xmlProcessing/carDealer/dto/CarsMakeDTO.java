package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsMakeDTO {
    @XmlElement(name = "car")
    private List<CarMakeDTO> cars;

    public CarsMakeDTO() {
    }

    public List<CarMakeDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarMakeDTO> cars) {
        this.cars = cars;
    }
}
