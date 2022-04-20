package softuni.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.TeamImportDTO;
import softuni.exam.domain.dto.TeamsRootImportDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;

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

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/teams.xml");

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String importTeams() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(TeamsRootImportDTO.class);
        Reader reader = Files.newBufferedReader(path);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TeamsRootImportDTO teamsRootImportDTO = (TeamsRootImportDTO) unmarshaller.unmarshal(reader);

        List<TeamImportDTO> teamImportDTOs = teamsRootImportDTO.getTeams();
        for (TeamImportDTO teamDTO : teamImportDTOs) {
            if (!teamDTO.isValid()) {
                message.add("Invalid team");
                continue;
            }
            Picture pictureByUrl = this.pictureRepository.findByUrl(teamDTO.getPicture().getUrl());
            if (pictureByUrl == null) {
                message.add("Invalid team");
                continue;
            }
            Team team = new Team();
            team.setName(teamDTO.getName());
            team.setPicture(pictureByUrl);
            this.teamRepository.save(team);
            message.add(String.format("Successfully imported - %s", team.getName()));
        }

        return String.join("\n", message);
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }
}
