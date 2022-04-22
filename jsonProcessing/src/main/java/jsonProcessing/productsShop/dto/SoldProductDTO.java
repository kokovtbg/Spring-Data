package jsonProcessing.productsShop.dto;

import java.util.Set;

public class SoldProductDTO {
    private int count;
    private Set<ProductBuyerDTO> products;

    public SoldProductDTO() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductBuyerDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductBuyerDTO> products) {
        this.products = products;
    }
}
