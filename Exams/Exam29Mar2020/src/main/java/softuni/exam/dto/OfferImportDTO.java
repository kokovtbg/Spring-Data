package softuni.exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {
    @XmlElement
    private String description;
    @XmlElement
    private BigDecimal price;
    @XmlElement(name = "added-on")
    private String addedOn;
    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;
    @XmlElement
    private CarIdDTO car;
    @XmlElement
    private SellerIdDTO seller;

    public OfferImportDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarIdDTO getCar() {
        return car;
    }

    public void setCar(CarIdDTO car) {
        this.car = car;
    }

    public SellerIdDTO getSeller() {
        return seller;
    }

    public void setSeller(SellerIdDTO seller) {
        this.seller = seller;
    }

    public boolean isValid() {
        if (this.price.doubleValue() <= 0) {
            return false;
        }
        if (this.description.length() < 5) {
            return false;
        }
        return true;
    }
}
