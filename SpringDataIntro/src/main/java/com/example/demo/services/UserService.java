package com.example.demo.services;

import com.example.demo.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<User> findByEmail(String provider);
    List<User> findByLastLogInBeforeAndSetToDeleted(LocalDateTime dateTime);
    void deleteUsers();
}
