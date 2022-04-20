package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.OfferImportDTO;
import softuni.exam.dto.OffersRootImportDTO;
import softuni.exam.models.Car;
import softuni.exam.models.Offer;
import softuni.exam.models.Picture;
import softuni.exam.models.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/offers.xml");
    private final ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.mapper = new ModelMapper();
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
        OffersRootImportDTO sellersRootImportDTO = (OffersRootImportDTO) unmarshaller.unmarshal(reader);

        List<OfferImportDTO> offerImportDTOs = sellersRootImportDTO.getOffers();
        for (OfferImportDTO offerDTO : offerImportDTOs) {
            if (!offerDTO.isValid()) {
                message.add("Invalid offer");
                continue;
            }
            String[] dateTimeData = offerDTO.getAddedOn().split(" ");
            String[] dateData = dateTimeData[0].split("-");
            int year = Integer.parseInt(dateData[0]);
            int month = Integer.parseInt(dateData[1]);
            int day = Integer.parseInt(dateData[2]);
            String[] timeData = dateTimeData[1].split(":");
            int hour = Integer.parseInt(timeData[0]);
            int minute = Integer.parseInt(timeData[1]);
            int second = Integer.parseInt(timeData[2]);
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
            Offer offerByDescriptionAndAddedOn = this.offerRepository.findByDescriptionAndAddedOn(offerDTO.getDescription(), dateTime);
            if (offerByDescriptionAndAddedOn != null) {
                message.add("Invalid offer");
                continue;
            }
            Offer offer = this.mapper.map(offerDTO, Offer.class);
            offer.setAddedOn(dateTime);
            Car car = this.carRepository.findById(offerDTO.getCar().getId()).get();
            Seller seller = this.sellerRepository.findById(offerDTO.getSeller().getId()).get();
            Set<Picture> pictures = car.getPictures();
            offer.setCar(car);
            offer.setSeller(seller);
            offer.setPictures(pictures);
            this.offerRepository.save(offer);
            message.add(String.format("Successfully import offer %s - %b", offer.getAddedOn(), offer.isHasGoldStatus()));
        }

        return String.join("\n", message);
    }
}
