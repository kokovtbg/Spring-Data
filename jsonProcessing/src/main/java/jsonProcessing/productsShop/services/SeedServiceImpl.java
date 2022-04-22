package jsonProcessing.productsShop.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonProcessing.productsShop.dto.CategoryRegisterDTO;
import jsonProcessing.productsShop.dto.ProductRegisterDTO;
import jsonProcessing.productsShop.dto.UserRegisterDTO;
import jsonProcessing.productsShop.entities.Category;
import jsonProcessing.productsShop.entities.Product;
import jsonProcessing.productsShop.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final Gson gson;
    private final ModelMapper mapper;
    private List<User> users;
    private List<Product> products;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/productsShop/users.json"));
        UserRegisterDTO[] userRegisterDTOs =
                this.gson.fromJson(reader, UserRegisterDTO[].class);
        this.users = Arrays.stream(userRegisterDTOs)
                .map(u -> mapper.map(u, User.class)).collect(Collectors.toList());
        this.users.forEach(this.userService::register);
    }

    @Override
    public void seedProducts() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/productsShop/products.json"));
        ProductRegisterDTO[] productRegisterDTOs =
                this.gson.fromJson(reader, ProductRegisterDTO[].class);
        this.products = Arrays.stream(productRegisterDTOs)
                .map(p -> mapper.map(p, Product.class))
                .map(p -> this.productService.getRandomSeller(p, this.users))
                .map(p -> this.productService.getRandomBuyer(p, this.users))
                .collect(Collectors.toList());
        this.products.forEach(this.productService::register);
    }

    @Override
    public void seedCategories() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/productsShop/categories.json"));
        CategoryRegisterDTO[] categoryRegisterDTOs =
                this.gson.fromJson(reader, CategoryRegisterDTO[].class);
        List<Category> categories = Arrays.stream(categoryRegisterDTOs)
                .map(c -> mapper.map(c, Category.class))
                .map(c -> this.categoryService.getRandomProducts(c, this.products))
                .collect(Collectors.toList());
        categories.forEach(this.categoryService::register);
    }
}
