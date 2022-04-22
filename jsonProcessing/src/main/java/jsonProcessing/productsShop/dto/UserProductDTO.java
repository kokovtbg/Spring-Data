package jsonProcessing.productsShop.dto;

import java.util.List;

public class UserProductDTO {
    private int usersCount;
    private List<UserAgeSoldProductDTO> users;

    public UserProductDTO() {
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserAgeSoldProductDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserAgeSoldProductDTO> users) {
        this.users = users;
    }
}
