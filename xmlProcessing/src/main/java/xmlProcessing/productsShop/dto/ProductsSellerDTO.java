package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSellerDTO {
    @XmlElement(name = "product")
    private List<ProductSellerDTO> products;

    public ProductsSellerDTO() {
    }

    public List<ProductSellerDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSellerDTO> products) {
        this.products = products;
    }
}
