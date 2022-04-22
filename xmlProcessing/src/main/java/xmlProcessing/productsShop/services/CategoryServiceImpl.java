package xmlProcessing.productsShop.services;

import xmlProcessing.productsShop.dto.CategoriesProductDTO;
import xmlProcessing.productsShop.dto.CategoryProductDTO;
import xmlProcessing.productsShop.entities.Category;
import xmlProcessing.productsShop.entities.Product;
import xmlProcessing.productsShop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Category getRandomProducts(Category category, List<Product> products) {
        Random random = new Random();
        Set<Product> randomProducts = new HashSet<>();

        int count = 0;
        while (count < 100) {
            randomProducts.add(products.get(random.nextInt(products.size())));
            count++;
        }
        category.setProducts(randomProducts);
        return category;
    }

    @Override
    public void register(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public CategoriesProductDTO getAllCategories() {
        List<Category> all = this.categoryRepository.findAll();
        List<CategoryProductDTO> categoryProductDTOs = all.stream()
                .map(c -> mapper.map(c, CategoryProductDTO.class))
                .collect(Collectors.toList());
        categoryProductDTOs = categoryProductDTOs.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getProducts().size(), c1.getProducts().size()))
                .collect(Collectors.toList());
        categoryProductDTOs.forEach(c -> {
            c.setProductsCount(c.getProducts().size());
            c.setAveragePrice(c.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).average().orElse(0));
            c.setTotalRevenue(c.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum());
            String patternAvg = "###.######";
            DecimalFormat format = new DecimalFormat(patternAvg);
            c.setAveragePrice(Double.parseDouble(format.format(c.getAveragePrice()).replace(",", ".")));
            String patternTotal = "#####.##";
            format = new DecimalFormat(patternTotal);
            c.setTotalRevenue(Double.parseDouble(format.format(c.getTotalRevenue()).replace(",", ".")));
        });
        CategoriesProductDTO categoriesProductDTO = new CategoriesProductDTO();
        categoriesProductDTO.setCategories(categoryProductDTOs);
        return categoriesProductDTO;
    }
}
