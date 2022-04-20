package exam.service.impl;

import exam.dto.ShopsImportDTO;
import exam.model.Shop;
import exam.model.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import org.modelmapper.ModelMapper;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/shops.xml");
    private final ModelMapper mapper;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importShops() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(ShopsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ShopsImportDTO shopsImportDTO = (ShopsImportDTO) unmarshaller.unmarshal(reader);

        shopsImportDTO.getShops()
                .forEach(s -> {
                    if (s.isValid()) {
                        Shop shopInDBByName = this.shopRepository.findByName(s.getName());
                        if (shopInDBByName != null) {
                            message.add("Invalid Shop");
                        } else {
                            Town townInRepoByName = this.townRepository.findByName(s.getTownNameDTO().getName());
                            s.setTown(townInRepoByName);
                            Shop shop = this.mapper.map(s, Shop.class);
                            this.shopRepository.save(shop);
                            message.add(String.format("Successfully imported Shop %s - %s",
                                    shop.getName(), shop.getIncome()));
                        }
                    } else {
                        message.add("Invalid Shop");
                    }
                });

        return String.join("\n", message);
    }
}
