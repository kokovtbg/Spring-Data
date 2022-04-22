package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductDTO {
    @XmlAttribute()
    private String name;
    @XmlTransient
    private Set<ProductPriceDTO> products;
    @XmlElement(name = "products-count")
    private int productsCount;
    @XmlElement(name = "average-price")
    private double averagePrice;
    @XmlElement(name = "total-revenue")
    private double totalRevenue;

    public CategoryProductDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductPriceDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductPriceDTO> products) {
        this.products = products;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
