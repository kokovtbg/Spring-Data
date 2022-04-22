package jsonProcessing.productsShop.services;

import jsonProcessing.productsShop.dto.ProductSellerDTO;
import jsonProcessing.productsShop.entities.Product;
import jsonProcessing.productsShop.entities.User;

import java.util.List;

public interface ProductService {
    Product getRandomSeller(Product product, List<User> users);

    Product getRandomBuyer(Product product, List<User> users);

    void register(Product product);

    List<ProductSellerDTO> getAllInPriceRangeWithoutBuyer();

}
