package jsonProcessing.carDealer.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarSerializeWithPartsDTO {
    @SerializedName(value = "Make")
    private String make;
    @SerializedName(value = "Model")
    private String model;
    @SerializedName(value = "TravelledDistance")
    private long travelledDistance;
    private List<PartRegisterDTO> parts;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartRegisterDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartRegisterDTO> parts) {
        this.parts = parts;
    }
}
