package jsonProcessing.carDealer.dto;

import com.google.gson.annotations.SerializedName;
import jsonProcessing.carDealer.entities.Part;

import java.util.List;

public class SupplierNotImporterDTO {
    @SerializedName(value = "Id")
    private int id;
    @SerializedName(value = "Name")
    private String name;
    private int partsCount;
    private List<Part> parts;

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

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
