package xmlProcessing.productsShop.services;

import xmlProcessing.productsShop.dto.UsersAgeSoldProductDTO;
import xmlProcessing.productsShop.dto.UsersSoldProductDTO;
import xmlProcessing.productsShop.entities.User;



public interface UserService {
    void register(User user);

    UsersSoldProductDTO getAllSorted();

    UsersAgeSoldProductDTO getAll();
}
