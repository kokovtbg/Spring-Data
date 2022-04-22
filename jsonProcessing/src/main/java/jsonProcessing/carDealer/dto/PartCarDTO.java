package jsonProcessing.carDealer.dto;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class PartCarDTO {
    @SerializedName(value = "Name")
    private String name;
    @SerializedName(value = "Price")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
