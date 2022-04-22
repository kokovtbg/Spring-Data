package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.CustomerWithoutSaleDTO;
import xmlProcessing.carDealer.dto.CustomerWithSaleDTO;
import xmlProcessing.carDealer.dto.CustomersWithSaleDTO;
import xmlProcessing.carDealer.dto.CustomersWithoutSaleDTO;
import xmlProcessing.carDealer.entities.Customer;
import xmlProcessing.carDealer.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void register(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public CustomersWithoutSaleDTO getAllSorted() {
        List<Customer> all = this.customerRepository.findAll(Sort.by("birthDate", "isYoungDriver"));
        List<CustomerWithoutSaleDTO> customerWithoutSaleDTOs = all.stream()
                .map(c -> mapper.map(c, CustomerWithoutSaleDTO.class))
                .collect(Collectors.toList());
        CustomersWithoutSaleDTO customersWithoutSaleDTO = new CustomersWithoutSaleDTO();
        customersWithoutSaleDTO.setCustomers(customerWithoutSaleDTOs);
        return customersWithoutSaleDTO;
    }

    @Override
    public CustomersWithSaleDTO getAll() {
        List<Customer> all = this.customerRepository.findAll();
        List<CustomerWithSaleDTO> customerWithSaleDTOs = all.stream()
                .map(c -> mapper.map(c, CustomerWithSaleDTO.class))
                .collect(Collectors.toList());
        customerWithSaleDTOs = customerWithSaleDTOs.stream()
                .filter(c -> !c.getSales().isEmpty())
                .collect(Collectors.toList());
        customerWithSaleDTOs.forEach(c -> {
            c.setBoughtCars(c.getSales().size());
            c.setSpentMoney(c.getSales().stream()
                    .mapToDouble(s -> s.getCar().getParts().stream()
                            .mapToDouble(p -> p.getPrice().doubleValue()).sum()).sum());
        });
        customerWithSaleDTOs = customerWithSaleDTOs.stream()
                .sorted((c1, c2) -> {
                    int result = Double.compare(c2.getSpentMoney(), c1.getSpentMoney());
                    if (result == 0) {
                        result = Integer.compare(c2.getBoughtCars(), c1.getBoughtCars());
                    }
                    return result;
                }).collect(Collectors.toList());
        CustomersWithSaleDTO customersWithSaleDTO = new CustomersWithSaleDTO();
        customersWithSaleDTO.setCustomers(customerWithSaleDTOs);
        return customersWithSaleDTO;
    }
}
