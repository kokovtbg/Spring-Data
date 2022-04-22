package jsonProcessing.carDealer.dto;


import com.google.gson.annotations.SerializedName;
import jsonProcessing.carDealer.entities.Sale;

import java.util.Set;

public class CustomerWithSaleDTO {
    @SerializedName(value = "fullName")
    private String name;
    private int boughtCars;
    private double spentMoney;
    private Set<Sale> sales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
