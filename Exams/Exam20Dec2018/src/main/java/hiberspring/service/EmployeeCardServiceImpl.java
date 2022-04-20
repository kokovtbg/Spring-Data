package hiberspring.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hiberspring.domain.dtos.EmployeeCardsDTO;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final EmployeeCardRepository employeeCardRepository;
    private final Path path = Paths.get("src/main/resources/files/employee-cards.json");
    private final Gson gson;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        EmployeeCardsDTO[] employeeCardsDTOs = this.gson.fromJson(reader, EmployeeCardsDTO[].class);
        for (EmployeeCardsDTO employeeCardDTO : employeeCardsDTOs) {
            EmployeeCard cardByNumber = this.employeeCardRepository.findByNumber(employeeCardDTO.getNumber());
            if (cardByNumber != null) {
                message.add("Error: Invalid data.");
                continue;
            }
            EmployeeCard employeeCard = new EmployeeCard();
            employeeCard.setNumber(employeeCardDTO.getNumber());
            this.employeeCardRepository.save(employeeCard);
            message.add(String.format("Successfully imported Employee Card %s.", employeeCard.getNumber()));
        }

        return String.join("\n", message);
    }
}
