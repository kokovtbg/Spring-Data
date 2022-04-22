package xmlProcessing.productsShop.services;

import xmlProcessing.productsShop.dto.*;
import xmlProcessing.productsShop.entities.User;
import xmlProcessing.productsShop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void register(User user) {
        this.userRepository.save(user);
    }

    @Override
    public UsersSoldProductDTO getAllSorted() {
        List<User> all = this.userRepository.findAll(Sort.by("lastName", "firstName"));
        List<UserSoldProductDTO> userSoldProductDTOs = all.stream()
                .map(u -> mapper.map(u, UserSoldProductDTO.class))
                .collect(Collectors.toList());
        userSoldProductDTOs
                .forEach(u -> u.getSoldProducts().setProducts(u.getSoldProducts().getProducts()
                        .stream().filter(p -> p.getBuyerLastName() != null).collect(Collectors.toList())));
        UsersSoldProductDTO usersSoldProductDTO = new UsersSoldProductDTO();
        usersSoldProductDTO.setUsers(userSoldProductDTOs);
        return usersSoldProductDTO;
    }

    @Override
    public UsersAgeSoldProductDTO getAll() {
        List<User> all = this.userRepository.findAll();
        all = all.stream()
                .filter(u -> !u.getSoldProducts().stream()
                        .filter(p -> p.getBuyer() != null).collect(Collectors.toSet()).isEmpty())
                .collect(Collectors.toList());
        List<UserAgeSoldProductDTO> userAgeSoldProductDTOs = all.stream()
                .map(u -> mapper.map(u, UserAgeSoldProductDTO.class))
                .collect(Collectors.toList());
        userAgeSoldProductDTOs
                .forEach(u -> u.getSoldProducts()
                        .setProducts(u.getSoldProducts().getProducts()
                                .stream().filter(p -> p.getBuyerLastName() != null).collect(Collectors.toSet())));
        userAgeSoldProductDTOs = userAgeSoldProductDTOs.stream()
                .sorted((u1, u2) -> {
                    int result = Integer.compare(u2.getSoldProducts().getProducts().size(),
                            u1.getSoldProducts().getProducts().size());
                    if (result == 0) {
                        result = u1.getLastName().compareTo(u2.getLastName());
                    }
                    return result;
                })
                .collect(Collectors.toList());
        userAgeSoldProductDTOs
                .forEach(u -> u.getSoldProducts().setCount(u.getSoldProducts().getProducts().size()));

        UsersAgeSoldProductDTO usersAgeSoldProductDTO = new UsersAgeSoldProductDTO();
        usersAgeSoldProductDTO.setUsers(userAgeSoldProductDTOs);
        usersAgeSoldProductDTO.setCount(userAgeSoldProductDTOs.size());
        return usersAgeSoldProductDTO;
    }
}
