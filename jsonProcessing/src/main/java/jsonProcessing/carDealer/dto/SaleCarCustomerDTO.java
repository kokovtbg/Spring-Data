package jsonProcessing.carDealer.dto;

import com.google.gson.annotations.SerializedName;

public class SaleCarCustomerDTO {
    private CarSerializeWithPartsDTO car;
    private String customerName;
    @SerializedName(value = "Discount")
    private double discount;
    private double price;
    private double priceWithDiscount;

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
