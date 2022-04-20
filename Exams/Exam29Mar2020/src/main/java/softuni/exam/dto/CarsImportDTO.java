package softuni.exam.dto;

public class CarsImportDTO {
    private String make;
    private String model;
    private int kilometers;
    private String registeredOn;

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

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public boolean isValid() {
        if (this.make.length() < 3 || this.make.length() > 19) {
            return false;
        }
        if (this.model.length() < 3 || this.model.length() > 19) {
            return false;
        }
        if (this.kilometers < 1) {
            return false;
        }
        return true;
    }
}
