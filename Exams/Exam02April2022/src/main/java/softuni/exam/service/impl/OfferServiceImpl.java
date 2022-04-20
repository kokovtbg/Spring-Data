package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferImportDTO;
import softuni.exam.models.dto.OffersRootImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

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

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/offers.xml");

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository) {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(OffersRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        OffersRootImportDTO offersRootImportDTO = (OffersRootImportDTO) unmarshaller.unmarshal(reader);

        List<OfferImportDTO> offerImportDTOs = offersRootImportDTO.getOffers();
        for (OfferImportDTO offerDTO : offerImportDTOs) {
            if (!offerDTO.isValid()) {
                message.add("Invalid offer");
                continue;
            }
            Agent agentByFirstName = this.agentRepository.findByFirstName(offerDTO.getAgent().getName());
            if (agentByFirstName == null) {
                message.add("Invalid offer");
                continue;
            }
            Apartment apartment = this.apartmentRepository.findById(offerDTO.getApartment().getId()).get();
            String[] dateData = offerDTO.getPublishedOn().split("/");
            int day = Integer.parseInt(dateData[0]);
            int month = Integer.parseInt(dateData[1]);
            int year = Integer.parseInt(dateData[2]);
            LocalDate date = LocalDate.of(year, month, day);
            Offer offer = new Offer();
            offer.setPrice(offerDTO.getPrice());
            offer.setPublishedOn(date);
            offer.setApartment(apartment);
            offer.setAgent(agentByFirstName);
            this.offerRepository.save(offer);
            message.add(String.format("Successfully imported offer %.2f", offer.getPrice()));
        }
        return String.join("\n", message);
    }

    @Override
    public String exportOffers() {
        List<String> offers = new ArrayList<>();

        List<Offer> offerList = this.offerRepository
                .findAllByApartmentApartmentTypeOrderByApartmentAreaDescPriceAsc(ApartmentType.ThreeRooms);
        offerList.forEach(o -> offers.add(o.toString()));

        return String.join("\n", offers);
    }
}
