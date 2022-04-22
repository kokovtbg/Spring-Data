package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.dto.CustomerWithSaleDTO;
import jsonProcessing.carDealer.dto.CustomerWithoutSaleDTO;
import jsonProcessing.carDealer.entities.Customer;

import java.util.List;
import java.util.function.Consumer;

public interface CustomerService {

    void register(Customer customer);

    List<CustomerWithoutSaleDTO> getAllSorted();

    List<CustomerWithSaleDTO> getAll();
}
