package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsBuyerDTO {
    @XmlElement(name = "product")
    private List<ProductBuyerDTO> products;

    public ProductsBuyerDTO() {
    }

    public List<ProductBuyerDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBuyerDTO> products) {
        this.products = products;
    }
}
