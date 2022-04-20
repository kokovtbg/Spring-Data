package com.example.football.service.impl;

import com.example.football.models.dto.PlayerExportDTO;
import com.example.football.models.dto.PlayerImportDTO;
import com.example.football.models.dto.PlayersImportDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/players.xml");
    private ModelMapper mapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TownRepository townRepository,
                             TeamRepository teamRepository, 
                             StatRepository statRepository) {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importPlayers() throws IOException, JAXBException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(PlayersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PlayersImportDTO playersImportDTO = (PlayersImportDTO) unmarshaller.unmarshal(reader);

        playersImportDTO.getPlayers()
                .forEach(p -> {
                    if (p.isValid()) {
                        Player playerInRepoByEmail = this.playerRepository.findByEmail(p.getEmail());
                        if (playerInRepoByEmail != null) {
                            message.add("Invalid Player");
                        } else {
                            String[] splitDate = p.getBirthDateString().split("/");
                            int day = Integer.parseInt(splitDate[0]);
                            int month = Integer.parseInt(splitDate[1]);
                            int year = Integer.parseInt(splitDate[2]);
                            p.setBirthDate(LocalDate.of(year, month, day));

                            this.mapper = new ModelMapper();
                            PropertyMap<PlayerImportDTO, Player> propertyMap = new PropertyMap<PlayerImportDTO, Player>() {
                                @Override
                                protected void configure() {
                                    map().setFirstName(source.getFirstName());
                                    map().setLastName(source.getLastName());
                                    map().setEmail(source.getEmail());
                                    map().setBirthDate(source.getBirthDate());
                                    map().setPosition(source.getPosition());
                                    skip().setId(0);
                                    skip().setTeam(null);
                                    skip().setTown(null);
                                    skip().setStat(null);
                                }
                            };
                            Player player = this.mapper.addMappings(propertyMap).map(p);
                            Town townInDBByName = this.townRepository.findByName(p.getTownName().getName());
                            Team teamInDBByName = this.teamRepository.findByName(p.getTeamName().getName());
                            Stat statInRepo = this.statRepository.findById(p.getStatIdDTO().getId()).get();
                            player.setTown(townInDBByName);
                            player.setTeam(teamInDBByName);
                            player.setStat(statInRepo);

                            this.playerRepository.save(player);
                            message.add(String.format("Successfully imported Player %s %s - %s",
                                    player.getFirstName(), player.getLastName(), player.getPosition()));
                        }
                    } else {
                        message.add("Invalid Player");
                    }
                });
        return String.join("\n", message);
    }

    @Override
    public String exportBestPlayers() {
        LocalDate dateBefore = LocalDate.of(1995, 1, 1);
        LocalDate dateAfter = LocalDate.of(2003, 1, 1);
        List<Player> allByBirthDateBetween = this.playerRepository
                .findAllByBirthDateBetween(dateBefore, dateAfter);
        allByBirthDateBetween = allByBirthDateBetween.stream()
                .sorted((p1, p2) -> {
                    int result = Float.compare(p2.getStat().getShooting(), p1.getStat().getShooting());
                    if (result == 0) {
                        result = Float.compare(p2.getStat().getPassing(), p1.getStat().getPassing());
                        if (result == 0) {
                            result = Float.compare(p2.getStat().getEndurance(), p1.getStat().getEndurance());
                            if (result == 0) {
                                result = p1.getLastName().compareTo(p2.getLastName());
                            }
                        }
                    }
                    return result;
                }).collect(Collectors.toList());
        List<PlayerExportDTO> playerExportDTOS = allByBirthDateBetween.stream()
                .map(p -> this.mapper.map(p, PlayerExportDTO.class))
                .collect(Collectors.toList());
        List<String> players = playerExportDTOS.stream().map(PlayerExportDTO::toString).collect(Collectors.toList());
        return String.join("\n", players);
    }
}
