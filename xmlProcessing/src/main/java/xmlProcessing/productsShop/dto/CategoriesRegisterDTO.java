package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesRegisterDTO {
    @XmlElement(name = "category")
    private List<CategoryRegisterDTO> categories;

    public CategoriesRegisterDTO() {
    }

    public List<CategoryRegisterDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryRegisterDTO> categories) {
        this.categories = categories;
    }
}
