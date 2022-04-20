package com.example.football.service.impl;

import com.example.football.models.dto.TeamsImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/json/teams.json");
    private final ModelMapper mapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importTeams() throws IOException {
        List<String> message = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader reader = Files.newBufferedReader(path);
        TeamsImportDTO[] teamsImportDTOS = gson.fromJson(reader, TeamsImportDTO[].class);
        Arrays.stream(teamsImportDTOS)
                .forEach(t -> {
                    if (t.isValid()) {
                        Team teamInRepoByName = this.teamRepository.findByName(t.getName());
                        if (teamInRepoByName != null) {
                            message.add("Invalid Team");
                        } else {
                            Town townInRepoByName = this.townRepository.findByName(t.getTownName());
                            t.setTown(townInRepoByName);
                            Team team = this.mapper.map(t, Team.class);
                            this.teamRepository.save(team);
                            message.add(String.format("Successfully imported Team %s - %d",
                                    team.getName(), team.getFanBase()));
                        }
                    } else {
                        message.add("Invalid Team");
                    }
                });
        return String.join("\n", message);
    }
}
