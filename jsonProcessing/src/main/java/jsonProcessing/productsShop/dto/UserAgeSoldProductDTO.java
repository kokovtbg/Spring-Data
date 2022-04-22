package jsonProcessing.productsShop.dto;

public class UserAgeSoldProductDTO {
    private String firstName;
    private String lastName;
    private int age;
    private SoldProductDTO soldProducts;

    public UserAgeSoldProductDTO() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductDTO getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductDTO soldProducts) {
        this.soldProducts = soldProducts;
    }
}
