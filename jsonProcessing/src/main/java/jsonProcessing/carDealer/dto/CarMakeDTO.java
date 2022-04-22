package jsonProcessing.carDealer.dto;

import com.google.gson.annotations.SerializedName;

public class CarMakeDTO {
    @SerializedName(value = "Id")
    private int id;
    @SerializedName(value = "Make")
    private String make;
    @SerializedName(value = "Model")
    private String model;
    @SerializedName(value = "TravelledDistance")
    private long travelledDistance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
