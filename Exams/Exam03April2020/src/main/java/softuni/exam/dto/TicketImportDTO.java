package softuni.exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketImportDTO {
    @XmlElement(name = "serial-number")
    private String serialNumber;
    @XmlElement
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeOff;
    @XmlElement(name = "from-town")
    private TownNameImportDTO fromTown;
    @XmlElement(name = "to-town")
    private TownNameImportDTO toTown;
    @XmlElement(name = "passenger")
    private PassengerNameDTO passenger;
    @XmlElement(name = "plane")
    private PlaneRegisterNumberDTO plane;


    public TicketImportDTO() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(String takeOff) {
        this.takeOff = takeOff;
    }

    public TownNameImportDTO getFromTown() {
        return fromTown;
    }

    public void setFromTown(TownNameImportDTO fromTown) {
        this.fromTown = fromTown;
    }

    public TownNameImportDTO getToTown() {
        return toTown;
    }

    public void setToTown(TownNameImportDTO toTown) {
        this.toTown = toTown;
    }

    public PassengerNameDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerNameDTO passenger) {
        this.passenger = passenger;
    }

    public PlaneRegisterNumberDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneRegisterNumberDTO plane) {
        this.plane = plane;
    }

    public boolean isValid() {
        if (this.serialNumber.length() < 2) {
            return false;
        }
        if (this.price.doubleValue() <= 0) {
            return false;
        }
        return true;
    }
}
