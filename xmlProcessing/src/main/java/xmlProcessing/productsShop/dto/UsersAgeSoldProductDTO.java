package xmlProcessing.productsShop.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAgeSoldProductDTO {
    @XmlAttribute
    private int count;
    @XmlElement(name = "user")
    private List<UserAgeSoldProductDTO> users;

    public UsersAgeSoldProductDTO() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserAgeSoldProductDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserAgeSoldProductDTO> users) {
        this.users = users;
    }
}
