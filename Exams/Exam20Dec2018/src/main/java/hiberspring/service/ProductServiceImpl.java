package hiberspring.service;

import hiberspring.domain.dtos.ProductImportDTO;
import hiberspring.domain.dtos.ProductsRootImportDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final Path path = Paths.get("src/main/resources/files/products.xml");

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importProducts() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(ProductsRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ProductsRootImportDTO productsRootImportDTO = (ProductsRootImportDTO) unmarshaller.unmarshal(reader);

        List<ProductImportDTO> productImportDTOs = productsRootImportDTO.getProducts();
        for (ProductImportDTO productDTO : productImportDTOs) {
            if (!productDTO.isValid()) {
                message.add("Error: Invalid data.");
                continue;
            }
            Branch branchByName = this.branchRepository.findByName(productDTO.getBranch());
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setClients(productDTO.getClients());
            product.setBranch(branchByName);
            this.productRepository.save(product);
            message.add(String.format("Successfully imported Product %s.", product.getName()));
        }

        return String.join("\n", message);
    }
}
