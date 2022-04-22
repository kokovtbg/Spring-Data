package spring.autoMapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.autoMapping.entities.User;
import spring.autoMapping.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User getById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
