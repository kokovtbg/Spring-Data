package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.*;
import xmlProcessing.carDealer.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlProcessing.productsShop.dto.UsersRegisterDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportServiceImpl implements ImportService {
    private final ModelMapper mapper;
    private List<Supplier> suppliers;
    private List<Part> parts;
    private List<Car> cars;
    private List<Customer> customers;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public ImportServiceImpl(SupplierService supplierService,
                             PartService partService,
                             CarService carService,
                             CustomerService customerService,
                             SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.mapper = new ModelMapper();
    }

    @Override
    public void importSuppliers() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/suppliers.xml"));

        JAXBContext context = JAXBContext.newInstance(SuppliersRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SuppliersRegisterDTO suppliersRegisterDTO = (SuppliersRegisterDTO) unmarshaller.unmarshal(reader);

        this.suppliers = suppliersRegisterDTO.getSuppliers().stream()
                .map(s -> mapper.map(s, Supplier.class))
                .collect(Collectors.toList());
        this.suppliers.forEach(this.supplierService::register);
    }

    @Override
    public void importParts() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/parts.xml"));

        JAXBContext context = JAXBContext.newInstance(PartsRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PartsRegisterDTO partsRegisterDTO = (PartsRegisterDTO) unmarshaller.unmarshal(reader);
        this.parts = partsRegisterDTO.getParts().stream()
                .map(p -> mapper.map(p, Part.class))
                .map(p -> this.partService.getRandomSupplier(p, suppliers))
                .collect(Collectors.toList());
        this.parts.forEach(this.partService::register);
    }

    @Override
    public void importCars() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/cars.xml"));

        JAXBContext context = JAXBContext.newInstance(CarsRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CarsRegisterDTO carsRegisterDTO = (CarsRegisterDTO) unmarshaller.unmarshal(reader);

        this.cars = carsRegisterDTO.getCars().stream()
                .map(c -> mapper.map(c, Car.class))
                .map(c -> this.carService.getRandomParts(c, parts))
                .collect(Collectors.toList());
        this.cars.forEach(this.carService::register);
    }

    @Override
    public void importCustomers() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/customers.xml"));

        JAXBContext context = JAXBContext.newInstance(CustomersRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CustomersRegisterDTO customersRegisterDTO = (CustomersRegisterDTO) unmarshaller.unmarshal(reader);

        this.customers = customersRegisterDTO.getCustomers().stream()
                .map(c -> mapper.map(c, Customer.class))
                .collect(Collectors.toList());
        this.customers.forEach(this.customerService::register);
    }

    @Override
    public void importSales() {
        List<Sale> sales = this.saleService.getRandomSales(cars, customers);
        sales.forEach(this.saleService::register);
    }
}
