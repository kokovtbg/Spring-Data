package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.dto.SaleCarCustomerDTO;
import jsonProcessing.carDealer.entities.Car;
import jsonProcessing.carDealer.entities.Customer;
import jsonProcessing.carDealer.entities.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getRandomSales(List<Car> cars, List<Customer> customers);

    void register(Sale sale);

    List<SaleCarCustomerDTO> getAll();
}
