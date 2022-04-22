package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsRegisterDTO {
    @XmlElement(name = "product")
    private List<ProductRegisterDTO> products;

    public ProductsRegisterDTO() {
    }

    public List<ProductRegisterDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRegisterDTO> products) {
        this.products = products;
    }
}
