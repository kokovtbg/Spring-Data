package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.CarMakeDTO;
import xmlProcessing.carDealer.dto.CarSerializeDTO;
import xmlProcessing.carDealer.dto.CarsPartDTO;
import xmlProcessing.carDealer.dto.CarsMakeDTO;
import xmlProcessing.carDealer.entities.Car;
import xmlProcessing.carDealer.entities.Part;
import xmlProcessing.carDealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Car getRandomParts(Car car, List<Part> parts) {
        Random random = new Random();
        Set<Part> partsForCar = new HashSet<>();
        List<Integer> countPartsList = Arrays.asList(3, 4, 5);
        int countParts = countPartsList.get(random.nextInt(countPartsList.size()));
        while (countParts > 0) {
            int indexPart = random.nextInt(parts.size());
            partsForCar.add(parts.get(indexPart));
            countParts--;
        }
        car.setParts(partsForCar);
        return car;
    }

    @Override
    public void register(Car car) {
        this.carRepository.save(car);
    }

    @Override
    public CarsMakeDTO getAllByMakeToyota() {
        List<Car> allByMake = this.carRepository
                .findAllByMake("Toyota",
                        Sort.by(Sort.Order.asc("model"), Sort.Order.desc("travelledDistance")));
        List<CarMakeDTO> carMakeDTOs = allByMake.stream()
                .map(c -> mapper.map(c, CarMakeDTO.class))
                .collect(Collectors.toList());
        CarsMakeDTO carsMakeDTO = new CarsMakeDTO();
        carsMakeDTO.setCars(carMakeDTOs);
        return carsMakeDTO;
    }

    @Override
    public CarsPartDTO getAll() {
        List<Car> all = this.carRepository.findAll();
        List<CarSerializeDTO> carSerializeDTOs = all.stream()
                .map(c -> mapper.map(c, CarSerializeDTO.class))
                .collect(Collectors.toList());
        CarsPartDTO carsPartDTO = new CarsPartDTO();
        carsPartDTO.setCars(carSerializeDTOs);
        return carsPartDTO;
    }
}
