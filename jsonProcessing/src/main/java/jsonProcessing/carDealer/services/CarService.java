package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.dto.CarMakeDTO;
import jsonProcessing.carDealer.dto.CarPartDTO;
import jsonProcessing.carDealer.entities.Car;
import jsonProcessing.carDealer.entities.Part;

import java.util.List;

public interface CarService {
    Car getRandomParts(Car car, List<Part> parts);

    void register(Car car);

    List<CarMakeDTO> getAllByMakeToyota();

    List<CarPartDTO> getAll();
}
