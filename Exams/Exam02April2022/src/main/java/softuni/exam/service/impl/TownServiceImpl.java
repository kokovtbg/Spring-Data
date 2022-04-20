package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownsImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

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
    private final Path path = Paths.get("src/main/resources/files/json/towns.json");
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importTowns() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        TownsImportDTO[] townsImportDTOs = this.gson.fromJson(reader, TownsImportDTO[].class);
        for (TownsImportDTO townDTO : townsImportDTOs) {
            if (!townDTO.isValid()) {
                message.add("Invalid town");
                continue;
            }
            Town town = this.mapper.map(townDTO, Town.class);
            this.townRepository.save(town);
            message.add(String.format("Successfully imported town %s - %d",
                    town.getTownName(), town.getPopulation()));
        }

        return String.join("\n", message);
    }
}
