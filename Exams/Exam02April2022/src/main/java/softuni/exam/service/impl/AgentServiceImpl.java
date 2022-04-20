package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentsImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/json/agents.json");
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importAgents() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        AgentsImportDTO[] agentsImportDTOs = this.gson.fromJson(reader, AgentsImportDTO[].class);
        for (AgentsImportDTO agentDTO : agentsImportDTOs) {
            if (!agentDTO.isValid()) {
                message.add("Invalid agent");
                continue;
            }
            Agent agentByFirstName = this.agentRepository.findByFirstName(agentDTO.getFirstName());
            if (agentByFirstName != null) {
                message.add("Invalid agent");
                continue;
            }
            Agent agent = this.mapper.map(agentDTO, Agent.class);
            Town townByTownName = this.townRepository.findByTownName(agentDTO.getTown());
            agent.setTown(townByTownName);
            this.agentRepository.save(agent);
            message.add(String.format("Successfully imported agent - %s %s",
                    agent.getFirstName(), agent.getLastName()));
        }

        return String.join("\n", message);
    }
}
