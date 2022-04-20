package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.CarsImportDTO;
import softuni.exam.models.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Path path = Paths.get("src/main/resources/files/json/cars.json");
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importCars() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        CarsImportDTO[] carsImportDTOs = this.gson.fromJson(reader, CarsImportDTO[].class);

        for (CarsImportDTO carDTO : carsImportDTOs) {
            if (!carDTO.isValid()) {
                message.add("Invalid car");
                continue;
            }
            Car carByMakeAndModelAndKilometers = this.carRepository
                    .findByMakeAndModelAndKilometers(carDTO.getMake(), carDTO.getModel(), carDTO.getKilometers());
            if (carByMakeAndModelAndKilometers != null) {
                message.add("Invalid car");
                continue;
            }
            Car car = this.mapper.map(carDTO, Car.class);
            String[] dateData = carDTO.getRegisteredOn().split("/");
            int day = Integer.parseInt(dateData[0]);
            int month = Integer.parseInt(dateData[1]);
            int year = Integer.parseInt(dateData[2]);
            LocalDate date = LocalDate.of(year, month, day);
            car.setRegisteredOn(date);
            this.carRepository.save(car);
            message.add(String.format("Successfully imported car - %s - %s", car.getMake(), car.getModel()));
        }

        return String.join("\n", message);
    }

    @Override
    @Transactional
    public String getCarsOrderByPicturesCountThenByMake() {
        Set<Car> allOrderByPicturesCountDescAndMakeAsc = this.carRepository.findAllOrderByPicturesCountDescAndMakeAsc();
        List<String> message = new ArrayList<>();
        allOrderByPicturesCountDescAndMakeAsc
                .forEach(c -> message.add(c.toString()));
        return String.join("\n", message);
    }
}
