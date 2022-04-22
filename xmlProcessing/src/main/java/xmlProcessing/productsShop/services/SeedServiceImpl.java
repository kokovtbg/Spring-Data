package xmlProcessing.productsShop.services;
import xmlProcessing.productsShop.dto.*;
import xmlProcessing.productsShop.entities.Category;
import xmlProcessing.productsShop.entities.Product;
import xmlProcessing.productsShop.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
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
        this.mapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/productsShop/users.xml"));

        JAXBContext context = JAXBContext.newInstance(UsersRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UsersRegisterDTO usersRegisterDTO = (UsersRegisterDTO) unmarshaller.unmarshal(reader);

        this.users = usersRegisterDTO.getUsers().stream()
                .map(u -> mapper.map(u, User.class)).collect(Collectors.toList());
        this.users.forEach(this.userService::register);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/productsShop/products.xml"));

        JAXBContext context = JAXBContext.newInstance(ProductsRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ProductsRegisterDTO productsRegisterDTO = (ProductsRegisterDTO) unmarshaller.unmarshal(reader);


        this.products = productsRegisterDTO.getProducts().stream()
                .map(p -> mapper.map(p, Product.class))
                .map(p -> this.productService.getRandomSeller(p, this.users))
                .map(p -> this.productService.getRandomBuyer(p, this.users))
                .collect(Collectors.toList());
        this.products.forEach(this.productService::register);
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        Reader reader = Files.newBufferedReader(Path.of("src/main/resources/files/productsShop/categories.xml"));

        JAXBContext context = JAXBContext.newInstance(CategoriesRegisterDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CategoriesRegisterDTO categoriesRegisterDTO = (CategoriesRegisterDTO) unmarshaller.unmarshal(reader);

        List<Category> categories = categoriesRegisterDTO.getCategories().stream()
                .map(c -> mapper.map(c, Category.class))
                .map(c -> this.categoryService.getRandomProducts(c, this.products))
                .collect(Collectors.toList());
        categories.forEach(this.categoryService::register);
    }
}
