package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsRootImportDTO {
    @XmlElement(name = "product")
    private List<ProductImportDTO> products;

    public ProductsRootImportDTO() {
    }

    public List<ProductImportDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductImportDTO> products) {
        this.products = products;
    }
}
