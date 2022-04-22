package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.CustomerWithSaleDTO;
import xmlProcessing.carDealer.dto.CustomerWithoutSaleDTO;
import xmlProcessing.carDealer.dto.CustomersWithSaleDTO;
import xmlProcessing.carDealer.dto.CustomersWithoutSaleDTO;
import xmlProcessing.carDealer.entities.Customer;

import java.util.List;

public interface CustomerService {

    void register(Customer customer);

    CustomersWithoutSaleDTO getAllSorted();

    CustomersWithSaleDTO getAll();
}
