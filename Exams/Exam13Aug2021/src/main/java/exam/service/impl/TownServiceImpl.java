package exam.service.impl;

import exam.dto.TownsImportDTO;
import exam.model.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
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
    private final Path path = Paths.get("src/main/resources/files/xml/towns.xml");
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
    public String importTowns() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(TownsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TownsImportDTO townsImportDTO = (TownsImportDTO) unmarshaller.unmarshal(reader);

        townsImportDTO.getTowns()
                .forEach(t -> {
                    if (t.isValid()) {
                        Town town = this.mapper.map(t, Town.class);
                        this.townRepository.save(town);
                        message.add(String.format("Successfully imported Town %s", town.getName()));
                    } else {
                        message.add("Invalid Town");
                    }
                });

        return String.join("\n", message);
    }
}
