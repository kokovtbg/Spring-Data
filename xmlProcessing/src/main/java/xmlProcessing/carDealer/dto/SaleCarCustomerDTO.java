package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleCarCustomerDTO {
    @XmlElement(name = "car")
    private CarSerializeWithPartsDTO car;
    @XmlElement(name = "customer-name")
    private String customerName;
    @XmlElement
    private double discount;
    @XmlElement
    private double price;
    @XmlElement(name = "price-with-discount")
    private double priceWithDiscount;

    public SaleCarCustomerDTO() {
    }

    public CarSerializeWithPartsDTO getCar() {
        return car;
    }

    public void setCar(CarSerializeWithPartsDTO car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
