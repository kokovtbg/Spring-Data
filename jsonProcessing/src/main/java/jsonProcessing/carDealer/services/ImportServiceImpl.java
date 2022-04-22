package jsonProcessing.carDealer.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonProcessing.carDealer.dto.CarRegisterDTO;
import jsonProcessing.carDealer.dto.CustomerRegisterDTO;
import jsonProcessing.carDealer.dto.PartRegisterDTO;
import jsonProcessing.carDealer.dto.SupplierRegisterDTO;
import jsonProcessing.carDealer.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportServiceImpl implements ImportService {
    private final Gson gson;
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
    public ImportServiceImpl(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public void importSuppliers() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/suppliers.json"));
        SupplierRegisterDTO[] supplierRegisterDTOs = this.gson.fromJson(reader, SupplierRegisterDTO[].class);
        this.suppliers = Arrays.stream(supplierRegisterDTOs)
                .map(s -> mapper.map(s, Supplier.class))
                .collect(Collectors.toList());
        this.suppliers.forEach(this.supplierService::register);
    }

    @Override
    public void importParts() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/parts.json"));
        PartRegisterDTO[] partRegisterDTOs = this.gson.fromJson(reader, PartRegisterDTO[].class);
        this.parts = Arrays.stream(partRegisterDTOs)
                .map(p -> mapper.map(p, Part.class))
                .map(p -> this.partService.getRandomSupplier(p, suppliers))
                .collect(Collectors.toList());
        this.parts.forEach(this.partService::register);
    }

    @Override
    public void importCars() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/cars.json"));
        CarRegisterDTO[] carRegisterDTOs = this.gson.fromJson(reader, CarRegisterDTO[].class);
        this.cars = Arrays.stream(carRegisterDTOs)
                .map(c -> mapper.map(c, Car.class))
                .map(c -> this.carService.getRandomParts(c, parts))
                .collect(Collectors.toList());
        this.cars.forEach(this.carService::register);
    }

    @Override
    public void importCustomers() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/carDealer/customers.json"));
        CustomerRegisterDTO[] customerRegisterDTOs = this.gson.fromJson(reader, CustomerRegisterDTO[].class);
        this.customers = Arrays.stream(customerRegisterDTOs)
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
