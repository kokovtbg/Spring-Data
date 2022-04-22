package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByEmail(String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public List<User> findByLastLogInBeforeAndSetToDeleted(LocalDateTime dateTime) {
        List<User> users =  this.userRepository.findAllByLastTimeLoggedInBefore(dateTime);
        users.forEach(u -> u.setDeleted(true));
        users.forEach(userRepository::save);
        return users;
    }

    @Override
    public void deleteUsers() {
        userRepository.deleteAllByIsDeletedTrue();
    }
}
