package xmlProcessing.productsShop.services;

import xmlProcessing.productsShop.dto.ProductSellerDTO;
import xmlProcessing.productsShop.dto.ProductsSellerDTO;
import xmlProcessing.productsShop.entities.Product;
import xmlProcessing.productsShop.entities.User;
import xmlProcessing.productsShop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Product getRandomSeller(Product product, List<User> users) {
        Random random = new Random();
        int index = random.nextInt(users.size());
        while (index == 0) {
            index = random.nextInt(users.size());
        }
        User seller = users.get(index);
        product.setSeller(seller);
        return product;
    }

    @Override
    public Product getRandomBuyer(Product product, List<User> users) {
        Random random = new Random();
        int index = random.nextInt(users.size());
        User buyer =
                index % 3 == 0
                ? null : users.get(index);
        product.setBuyer(buyer);
        return product;
    }

    @Override
    public void register(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public ProductsSellerDTO getAllInPriceRangeWithoutBuyer() {
        List<Product> allByPriceBetweenAndBuyerIsNull = this.productRepository
                .findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        List<ProductSellerDTO> productSellerDTOs = allByPriceBetweenAndBuyerIsNull.stream()
                .map(p -> mapper.map(p, ProductSellerDTO.class))
                .collect(Collectors.toList());
        productSellerDTOs = productSellerDTOs.stream()
                .sorted(Comparator.comparing(ProductSellerDTO::getPrice)).collect(Collectors.toList());
        productSellerDTOs
                .forEach(p -> {
                    p.setSeller(p.getSellerFirstName() == null ?
                            p.getSellerLastName() : p.getSellerFirstName() + " " + p.getSellerLastName());
                    p.setSellerFirstName(null);
                    p.setSellerLastName(null);
                });
        ProductsSellerDTO productsSellerDTO = new ProductsSellerDTO();
        productsSellerDTO.setProducts(productSellerDTOs);
        return productsSellerDTO;
    }

}
