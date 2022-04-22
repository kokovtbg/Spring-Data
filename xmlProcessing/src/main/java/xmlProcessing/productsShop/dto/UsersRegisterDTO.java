package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersRegisterDTO {
    @XmlElement(name = "user")
    private List<UserRegisterDTO> users;

    public UsersRegisterDTO() {
    }

    public List<UserRegisterDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserRegisterDTO> users) {
        this.users = users;
    }
}
