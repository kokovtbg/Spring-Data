package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.SaleCarCustomerDTO;
import xmlProcessing.carDealer.dto.SalesCarCustomerDTO;
import xmlProcessing.carDealer.entities.Car;
import xmlProcessing.carDealer.entities.Customer;
import xmlProcessing.carDealer.entities.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getRandomSales(List<Car> cars, List<Customer> customers);

    void register(Sale sale);

    SalesCarCustomerDTO getAll();
}
