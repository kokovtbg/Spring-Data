package xmlProcessing.productsShop.services;

import xmlProcessing.productsShop.dto.CategoriesProductDTO;
import xmlProcessing.productsShop.dto.CategoryProductDTO;
import xmlProcessing.productsShop.entities.Category;
import xmlProcessing.productsShop.entities.Product;

import java.util.List;

public interface CategoryService {
    Category getRandomProducts(Category category, List<Product> products);

    void register(Category category);

    CategoriesProductDTO getAllCategories();
}
