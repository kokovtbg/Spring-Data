package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductDTO {
    @XmlElement(name = "user")
    private List<UserSoldProductDTO> users;

    public UsersSoldProductDTO() {
    }

    public List<UserSoldProductDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldProductDTO> users) {
        this.users = users;
    }
}
