package xmlProcessing.productsShop.services;

import xmlProcessing.productsShop.dto.ProductsSellerDTO;
import xmlProcessing.productsShop.entities.Product;
import xmlProcessing.productsShop.entities.User;

import java.util.List;

public interface ProductService {
    Product getRandomSeller(Product product, List<User> users);

    Product getRandomBuyer(Product product, List<User> users);

    void register(Product product);

    ProductsSellerDTO getAllInPriceRangeWithoutBuyer();

}
