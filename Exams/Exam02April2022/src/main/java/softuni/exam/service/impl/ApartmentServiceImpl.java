package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentImportDTO;
import softuni.exam.models.dto.ApartmentsRootImportDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/apartments.xml");

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(ApartmentsRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ApartmentsRootImportDTO apartmentsRootImportDTO = (ApartmentsRootImportDTO) unmarshaller.unmarshal(reader);

        List<ApartmentImportDTO> apartmentImportDTOs = apartmentsRootImportDTO.getApartments();
        for (ApartmentImportDTO apartmentDTO : apartmentImportDTOs) {
            if (!apartmentDTO.isValid()) {
                message.add("Invalid apartment");
                continue;
            }
            Apartment apartmentByTownTownNameAndArea = this.apartmentRepository
                    .findByTownTownNameAndArea(apartmentDTO.getTown(), apartmentDTO.getArea());
            if (apartmentByTownTownNameAndArea != null) {
                message.add("Invalid apartment");
                continue;
            }
            Town townByTownName = this.townRepository.findByTownName(apartmentDTO.getTown());
            ApartmentType apartmentType = Arrays.stream(ApartmentType.values())
                    .filter(a -> a.getValue().equals(apartmentDTO.getApartmentType())).findFirst().orElse(null);
            Apartment apartment = new Apartment();
            apartment.setApartmentType(apartmentType);
            apartment.setArea(apartmentDTO.getArea());
            apartment.setTown(townByTownName);
            this.apartmentRepository.save(apartment);
            message.add(String.format("Successfully imported apartment %s - %.2f",
                    apartment.getApartmentType(), apartment.getArea()));
        }

        return String.join("\n", message);
    }
}
