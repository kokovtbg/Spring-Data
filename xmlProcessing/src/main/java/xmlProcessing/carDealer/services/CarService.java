package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.CarsPartDTO;
import xmlProcessing.carDealer.dto.CarsMakeDTO;
import xmlProcessing.carDealer.entities.Car;
import xmlProcessing.carDealer.entities.Part;

import java.util.List;

public interface CarService {
    Car getRandomParts(Car car, List<Part> parts);

    void register(Car car);

    CarsMakeDTO getAllByMakeToyota();

    CarsPartDTO getAll();
}
