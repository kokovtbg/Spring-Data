package jsonProcessing.productsShop.services;

import java.io.IOException;

public interface SeedService {
    void seedUsers() throws IOException;

    void seedProducts() throws IOException;

    void seedCategories() throws IOException;

    default void seedAll() throws IOException {
        seedUsers();
        seedProducts();
        seedCategories();
    }
}
