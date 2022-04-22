package jsonProcessing.productsShop.dto;

import java.math.BigDecimal;

public class ProductPriceDTO {
    private BigDecimal price;

    public ProductPriceDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
