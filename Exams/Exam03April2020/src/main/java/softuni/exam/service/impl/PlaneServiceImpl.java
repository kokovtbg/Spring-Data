package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.PlaneImportDTO;
import softuni.exam.dto.PlanesRootImportDTO;
import softuni.exam.models.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;

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
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/planes.xml");
    private final ModelMapper mapper;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importPlanes() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(PlanesRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PlanesRootImportDTO planesRootImportDTO = (PlanesRootImportDTO) unmarshaller.unmarshal(reader);

        List<PlaneImportDTO> planeImportDTOs = planesRootImportDTO.getPlanes();
        for (PlaneImportDTO planeDTO : planeImportDTOs) {
            if (!planeDTO.isValid()) {
                message.add("Invalid Plane");
                continue;
            }
            Plane plane = this.mapper.map(planeDTO, Plane.class);
            this.planeRepository.save(plane);
            message.add(String.format("Successfully imported Plane %s", plane.getRegisterNumber()));
        }

        return String.join("\n", message);
    }
}
