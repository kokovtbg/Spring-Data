package jsonProcessing.productsShop.dto;

import java.util.Set;

public class UserSoldProductDTO {
    private String firstName;
    private String lastName;
    private Set<ProductBuyerDTO> soldProducts;

    public UserSoldProductDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductBuyerDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductBuyerDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
