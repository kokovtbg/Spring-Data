package jsonProcessing.carDealer.dto;

import java.util.List;

public class CarPartDTO {
    private CarSerializeDTO car;
    private List<PartCarDTO> parts;

    public CarSerializeDTO getCar() {
        return car;
    }

    public void setCar(CarSerializeDTO car) {
        this.car = car;
    }

    public List<PartCarDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartCarDTO> parts) {
        this.parts = parts;
    }
}
