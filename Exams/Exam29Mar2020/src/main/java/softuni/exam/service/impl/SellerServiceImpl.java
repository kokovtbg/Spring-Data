package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.SellerImportDTO;
import softuni.exam.dto.SellersRootImportDTO;
import softuni.exam.models.Rating;
import softuni.exam.models.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;

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
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/sellers.xml");
    private final ModelMapper mapper;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(SellersRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SellersRootImportDTO sellersRootImportDTO = (SellersRootImportDTO) unmarshaller.unmarshal(reader);

        List<SellerImportDTO> sellerImportDTOs = sellersRootImportDTO.getSellers();
        for (SellerImportDTO sellerDTO : sellerImportDTOs) {
            if (!sellerDTO.isValid()) {
                message.add("Invalid seller");
                continue;
            }
            Seller sellerByEmail = this.sellerRepository.findByEmail(sellerDTO.getEmail());
            if (sellerByEmail != null) {
                message.add("Invalid seller");
                continue;
            }
            Seller seller = this.mapper.map(sellerDTO, Seller.class);
            Rating rating = Rating.valueOf(sellerDTO.getRating());
            seller.setRating(rating);
            this.sellerRepository.save(seller);
            message.add(String.format("Successfully import seller %s - %s",
                    seller.getLastName(), seller.getEmail()));
        }

        return String.join("\n", message);
    }
}
