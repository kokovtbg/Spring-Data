package jsonProcessing.productsShop.dto;

import java.util.Set;

public class CategoryProductDTO {
    private String name;
    private Set<ProductPriceDTO> products;
    private String category;
    private int productsCount;
    private double averagePrice;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
