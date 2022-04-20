package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.PassengersImportDTO;
import softuni.exam.models.Passenger;
import softuni.exam.models.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/json/passengers.json");
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, TownRepository townRepository) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importPassengers() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        PassengersImportDTO[] passengersImportDTOs = this.gson.fromJson(reader, PassengersImportDTO[].class);

        for (PassengersImportDTO passengerDTO : passengersImportDTOs) {
            if (!passengerDTO.isValid()) {
                message.add("Invalid Passenger");
                continue;
            }
            Passenger passenger = this.mapper.map(passengerDTO, Passenger.class);
            Town townByName = this.townRepository.findByName(passengerDTO.getTown());
            passenger.setTown(townByName);
            this.passengerRepository.save(passenger);
            message.add(String.format("Successfully imported Passenger %s - %s",
                    passenger.getLastName(), passenger.getEmail()));
        }

        return String.join("\n", message);
    }

    @Override
    @Transactional
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        Set<Passenger> allOrderByTicketsCountAndEmail = this.passengerRepository.findAllOrderByTicketsCountAndEmail();

        List<String> message = new ArrayList<>();
        allOrderByTicketsCountAndEmail
                .forEach(p -> message.add(p.toString()));

        return String.join("\n", message);
    }
}
