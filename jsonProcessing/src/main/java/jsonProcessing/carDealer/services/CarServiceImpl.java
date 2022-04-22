package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.dto.CarMakeDTO;
import jsonProcessing.carDealer.dto.CarPartDTO;
import jsonProcessing.carDealer.entities.Car;
import jsonProcessing.carDealer.entities.Part;
import jsonProcessing.carDealer.repositories.CarRepository;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<CarMakeDTO> getAllByMakeToyota() {
        List<Car> allByMake = this.carRepository
                .findAllByMake("Toyota",
                        Sort.by(Sort.Order.asc("model"), Sort.Order.desc("travelledDistance")));
        List<CarMakeDTO> carMakeDTOs = allByMake.stream()
                .map(c -> mapper.map(c, CarMakeDTO.class))
                .collect(Collectors.toList());
        return carMakeDTOs;
    }

    @Override
    public List<CarPartDTO> getAll() {
        List<Car> all = this.carRepository.findAll();
        List<CarPartDTO> carPartDTOs = all.stream()
                .map(c -> mapper.map(c, CarPartDTO.class))
                .collect(Collectors.toList());
        return carPartDTOs;
    }
}
