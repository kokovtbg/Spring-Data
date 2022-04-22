package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductDTO {
    @XmlAttribute
    private int count;
    @XmlElement(name = "product")
    private Set<ProductNamePriceDTO> products;

    public SoldProductDTO() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductNamePriceDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductNamePriceDTO> products) {
        this.products = products;
    }
}
