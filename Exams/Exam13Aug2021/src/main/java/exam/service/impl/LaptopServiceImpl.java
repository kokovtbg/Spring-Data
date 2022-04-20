package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.dto.LaptopExportDTO;
import exam.dto.LaptopsImportDTO;
import exam.model.Laptop;
import exam.model.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final Path path = Paths.get("src/main/resources/files/json/laptops.json");
    private ModelMapper mapper;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importLaptops() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        Gson gson = new GsonBuilder().create();
        LaptopsImportDTO[] laptopsImportDTOS = gson.fromJson(reader, LaptopsImportDTO[].class);

        Arrays.stream(laptopsImportDTOS)
                .forEach(l -> {
                    if (l.isValid()) {
                        Laptop laptopInDBByMacAddress = this.laptopRepository.findByMacAddress(l.getMacAddress());
                        if (laptopInDBByMacAddress != null) {
                            message.add("Invalid Laptop");
                        } else {
                            Shop shopInDBbyName = this.shopRepository.findByName(l.getShopNameDTO().getName());
                            l.setShopInRepo(shopInDBbyName);

                            PropertyMap<LaptopsImportDTO, Laptop> propertyMap = new PropertyMap<LaptopsImportDTO, Laptop>() {
                                @Override
                                protected void configure() {
                                    map().setShop(source.getShopInRepo());
                                }
                            };
                            this.mapper = new ModelMapper();
                            Laptop laptop = this.mapper.addMappings(propertyMap).map(l);

                            this.laptopRepository.save(laptop);
                            message.add(String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                                    laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage()));
                        }
                    } else {
                        message.add("Invalid Laptop");
                    }
                });

        return String.join("\n", message);
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> all = this.laptopRepository.findAll(
                Sort.by(Sort.Order.desc("cpuSpeed"), Sort.Order.desc("ram"),
                        Sort.Order.desc("storage"), Sort.Order.asc("macAddress")));
        List<LaptopExportDTO> laptopExportDTOS = all.stream().map(l -> this.mapper.map(l, LaptopExportDTO.class))
                .collect(Collectors.toList());
        List<String> collect = laptopExportDTOS.stream().map(LaptopExportDTO::toString).collect(Collectors.toList());
        return String.join("\n", collect);
    }
}
