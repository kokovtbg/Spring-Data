package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.SaleCarCustomerDTO;
import xmlProcessing.carDealer.dto.SalesCarCustomerDTO;
import xmlProcessing.carDealer.entities.Car;
import xmlProcessing.carDealer.entities.Customer;
import xmlProcessing.carDealer.entities.Sale;
import xmlProcessing.carDealer.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<Sale> getRandomSales(List<Car> cars, List<Customer> customers) {
        List<Sale> sales = new ArrayList<>();
        Random random = new Random();
        int count = 50;
        while (count > 0) {
            int indexCar = random.nextInt(cars.size());
            int indexCustomer = random.nextInt(customers.size());
            Car car = cars.get(indexCar);
            Customer customer = customers.get(indexCustomer);
            List<Double> discountList = Arrays.asList(0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5);
            int indexDiscount = random.nextInt(discountList.size());
            double discount = discountList.get(indexDiscount);
            Sale sale = new Sale();
            sale.setCar(car);
            sale.setCustomer(customer);
            sale.setDiscount(discount);
            sales.add(sale);
            count--;
        }
        return sales;
    }

    @Override
    public void register(Sale sale) {
        this.saleRepository.save(sale);
    }

    @Override
    public SalesCarCustomerDTO getAll() {
        List<Sale> all = this.saleRepository.findAll();
        List<SaleCarCustomerDTO> saleCarCustomerDTOs = all.stream()
                .map(s -> mapper.map(s, SaleCarCustomerDTO.class))
                .collect(Collectors.toList());
        saleCarCustomerDTOs
                .forEach(s -> {
                    s.setPrice(s.getCar().getParts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum());
                    s.setPriceWithDiscount(s.getPrice() * (1 - s.getDiscount()));
                    s.getCar().setParts(null);
                });
        SalesCarCustomerDTO salesCarCustomerDTO = new SalesCarCustomerDTO();
        salesCarCustomerDTO.setSales(saleCarCustomerDTOs);
        return salesCarCustomerDTO;
    }
}
