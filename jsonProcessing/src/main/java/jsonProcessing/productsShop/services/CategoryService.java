package jsonProcessing.productsShop.services;

import jsonProcessing.productsShop.dto.CategoryProductDTO;
import jsonProcessing.productsShop.entities.Category;
import jsonProcessing.productsShop.entities.Product;

import java.util.List;

public interface CategoryService {
    Category getRandomProducts(Category category, List<Product> products);

    void register(Category category);

    List<CategoryProductDTO> getAllCategories();
}
