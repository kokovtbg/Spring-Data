package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.dto.CustomersImportDTO;
import exam.model.Customer;
import exam.model.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Path path = Paths.get("src/main/resources/files/json/customers.json");
    private ModelMapper mapper;
    private final TownRepository townRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importCustomers() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        Gson gson = new GsonBuilder().create();
        CustomersImportDTO[] customersImportDTOS = gson.fromJson(reader, CustomersImportDTO[].class);

        Arrays.stream(customersImportDTOS)
                .forEach(c -> {
                    if (c.isValid()) {
                        Customer customerInDBByEmail = this.customerRepository.findByEmail(c.getEmail());
                        if (customerInDBByEmail != null) {
                            message.add("Invalid Customer");
                        } else {
                            Town townInRepoByName = this.townRepository.findByName(c.getTownNameDTO().getName());
                            c.setTownInRepo(townInRepoByName);
                            String[] splitData = c.getRegisteredOnString().split("/");
                            int day = Integer.parseInt(splitData[0]);
                            int month = Integer.parseInt(splitData[1]);
                            int year = Integer.parseInt(splitData[2]);
                            c.setRegisteredOnDate(LocalDate.of(year, month, day));

//                            TypeMap<CustomersImportDTO, Customer> typeMap =
//                                    mapper.createTypeMap(CustomersImportDTO.class, Customer.class);
//                            typeMap.addMappings(m -> m.map(CustomersImportDTO::getRegisteredOnDate, Customer::setRegisteredOn))
//                                    .addMappings(m -> m.map(CustomersImportDTO::getTownInRepo, Customer::setTown));
//                            Customer customer = typeMap.map(c);
                            PropertyMap<CustomersImportDTO, Customer> propertyMap = new PropertyMap<CustomersImportDTO, Customer>() {
                                @Override
                                protected void configure() {
                                    map().setRegisteredOn(source.getRegisteredOnDate());
                                    map().setTown(source.getTownInRepo());
                                }
                            };
                            this.mapper = new ModelMapper();
                            Customer customer = mapper.addMappings(propertyMap).map(c);

                            this.customerRepository.save(customer);
                            message.add(String.format("Successfully imported Customer %s %s - %s",
                                    customer.getFirstName(), customer.getLastName(), customer.getEmail()));
                        }
                    } else {
                        message.add("Invalid Customer");
                    }
                });

        return String.join("\n", message);
    }
}
