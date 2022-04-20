package com.example.football.service.impl;

import com.example.football.models.dto.TownsImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/json/towns.json");
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importTowns() throws IOException {
        List<String> message = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader reader = Files.newBufferedReader(path);
        TownsImportDTO[] townsImportDTOS = gson.fromJson(reader, TownsImportDTO[].class);

        Arrays.stream(townsImportDTOS)
                .forEach(t -> {
                    if (t.isValid()) {
                        Town townInRepo = this.townRepository.findByName(t.getName());
                        if (townInRepo != null) {
                            message.add("Invalid Town");
                        } else {
                            Town townToPersist = this.mapper.map(t, Town.class);
                            this.townRepository.save(townToPersist);
                            message.add(String.format("Successfully imported Town %s - %d",
                                    townToPersist.getName(), townToPersist.getPopulation()));
                        }
                    } else {
                        message.add("Invalid Town");
                    }
                });
        return String.join("\n", message);
    }
}
