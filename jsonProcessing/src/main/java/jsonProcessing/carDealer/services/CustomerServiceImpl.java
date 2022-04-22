package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.dto.CustomerWithSaleDTO;
import jsonProcessing.carDealer.dto.CustomerWithoutSaleDTO;
import jsonProcessing.carDealer.entities.Customer;
import jsonProcessing.carDealer.repositories.CustomerRepository;
import net.bytebuddy.TypeCache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<CustomerWithoutSaleDTO> getAllSorted() {
        List<Customer> all = this.customerRepository.findAll(Sort.by("birthDate", "isYoungDriver"));
        List<CustomerWithoutSaleDTO> customerWithoutSaleDTOs = all.stream()
                .map(c -> mapper.map(c, CustomerWithoutSaleDTO.class))
                .collect(Collectors.toList());
        return customerWithoutSaleDTOs;
    }

    @Override
    public List<CustomerWithSaleDTO> getAll() {
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
            c.setSales(null);
        });
        customerWithSaleDTOs = customerWithSaleDTOs.stream()
                .sorted((c1, c2) -> {
                    int result = Double.compare(c2.getSpentMoney(), c1.getSpentMoney());
                    if (result == 0) {
                        result = Integer.compare(c2.getBoughtCars(), c1.getBoughtCars());
                    }
                    return result;
                }).collect(Collectors.toList());
        return customerWithSaleDTOs;
    }
}
