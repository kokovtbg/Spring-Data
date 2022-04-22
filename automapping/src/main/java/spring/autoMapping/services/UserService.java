package spring.autoMapping.services;

import spring.autoMapping.entities.User;

public interface UserService {
    void register(User user);

    User getByEmail(String email);

    User getById(int id);
}
