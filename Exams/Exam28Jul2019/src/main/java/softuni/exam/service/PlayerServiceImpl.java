package softuni.exam.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PlayersImportDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Position;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;
    private final Path path = Paths.get("src/main/resources/files/json/players.json");
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             PictureRepository pictureRepository,
                             TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public String importPlayers() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        PlayersImportDTO[] playersImportDTOs = this.gson.fromJson(reader, PlayersImportDTO[].class);
        for (PlayersImportDTO playerDTO : playersImportDTOs) {
            if (!playerDTO.isValid()) {
                message.add("Invalid player");
                continue;
            }
            Picture pictureByUrl = this.pictureRepository.findByUrl(playerDTO.getPicture().getUrl());
            if (pictureByUrl == null) {
                message.add("Invalid player");
                continue;
            }
            Team teamByName = this.teamRepository.findByName(playerDTO.getTeam().getName());
            if (teamByName == null) {
                message.add("Invalid player");
                continue;
            }
            Position position = Position.valueOf(playerDTO.getPosition());
            Player player = this.mapper.map(playerDTO, Player.class);
            player.setPosition(position);
            player.setPicture(pictureByUrl);
            player.setTeam(teamByName);
            this.playerRepository.save(player);
            message.add(String.format("Successfully imported player: %s %s",
                    player.getFirstName(), player.getLastName()));
        }

        return String.join("\n", message);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        List<String> message = new ArrayList<>();
        List<Player> allBySalaryGreaterThanOrderBySalaryDesc = this.playerRepository
                .findAllBySalaryGreaterThanOrderBySalaryDesc(BigDecimal.valueOf(100000));
        allBySalaryGreaterThanOrderBySalaryDesc
                .forEach(p -> message.add(String.format("Player name: %s %s \n" +
                        "\tNumber: %d\n" +
                        "\tSalary: %s\n" +
                        "\tTeam: %s", p.getFirstName(), p.getLastName(), p.getNumber(),
                        p.getSalary(), p.getTeam().getName())));
        return String.join("\n", message);
    }

    @Override
    @Transactional
    public String exportPlayersInATeam() {
        List<String> message = new ArrayList<>();
        message.add("Team: North Hub");
        Team northHub = this.teamRepository.findByName("North Hub");
        northHub.getPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getId))
                .forEach(p -> message.add(p.toString()));
        return String.join("\n", message);
    }
}
