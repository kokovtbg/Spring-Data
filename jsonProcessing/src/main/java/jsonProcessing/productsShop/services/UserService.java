package jsonProcessing.productsShop.services;

import jsonProcessing.productsShop.dto.UserProductDTO;
import jsonProcessing.productsShop.dto.UserSoldProductDTO;
import jsonProcessing.productsShop.entities.User;

import java.util.List;


public interface UserService {
    void register(User user);

    List<UserSoldProductDTO> getAllSorted();

    UserProductDTO getAll();
}
