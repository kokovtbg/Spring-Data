package jsonProcessing.carDealer.dto;

import com.google.gson.annotations.SerializedName;
import jsonProcessing.carDealer.entities.Sale;

import java.util.HashSet;
import java.util.Set;

public class CustomerWithoutSaleDTO {
    @SerializedName(value = "Id")
    private int id;
    @SerializedName(value = "Name")
    private String name;
    @SerializedName(value = "BirthDate")
    private String birthDate;
    @SerializedName(value = "IsYoungDriver")
    private boolean isYoungDriver;
    @SerializedName(value = "Sales")
    private Set<Sale> salesEmpty = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getSalesEmpty() {
        return salesEmpty;
    }

    public void setSalesEmpty(Set<Sale> salesEmpty) {
        this.salesEmpty = salesEmpty;
    }
}
