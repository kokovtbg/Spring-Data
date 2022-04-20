package hiberspring.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hiberspring.domain.dtos.TownsImportDTO;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
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
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/towns.json");
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        TownsImportDTO[] townsImportDTOs = this.gson.fromJson(reader, TownsImportDTO[].class);
        for (TownsImportDTO townDTO : townsImportDTOs) {
            if (!townDTO.isValid()) {
                message.add("Error: Invalid data.");
                continue;
            }
            Town town = new Town();
            town.setName(townDTO.getName());
            town.setPopulation(townDTO.getPopulation());
            this.townRepository.save(town);
            message.add(String.format("Successfully imported Town %s.", town.getName()));
        }

        return String.join("\n", message);
    }
}
