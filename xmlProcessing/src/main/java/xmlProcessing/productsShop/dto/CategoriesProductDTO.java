package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesProductDTO {
    @XmlElement(name = "category")
    private List<CategoryProductDTO> categories;

    public CategoriesProductDTO() {
    }

    public List<CategoryProductDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryProductDTO> categories) {
        this.categories = categories;
    }
}
