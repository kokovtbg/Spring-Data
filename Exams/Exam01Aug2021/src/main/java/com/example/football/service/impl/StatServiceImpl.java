package com.example.football.service.impl;

import com.example.football.models.dto.StatsImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/stats.xml");
    private final ModelMapper mapper;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) {
        this.statRepository = statRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importStats() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(StatsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StatsImportDTO statsImportDTO = (StatsImportDTO) unmarshaller.unmarshal(reader);

        statsImportDTO.getStats()
                .forEach(s -> {
                    if (s.isValid()) {
                        Stat statInRepoByPSE = this.statRepository
                                .findByPassingAndShootingAndEndurance(s.getPassing(), s.getShooting(), s.getEndurance());
                        if (statInRepoByPSE != null) {
                            message.add("Invalid Stat");
                        } else {
                            Stat stat = this.mapper.map(s, Stat.class);
                            this.statRepository.save(stat);
                            message.add(String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                    stat.getPassing(), stat.getShooting(), stat.getEndurance()));
                        }
                    } else {
                        message.add("Invalid Stat");
                    }
                });

        return String.join("\n", message);
    }
}
