package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FillServiceImpl implements FillService {
    private UserRepository userRepository;

    @Autowired
    public FillServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void fill() {
        User user = new User();
        user.setUsername("johnny30");
        user.setPassword("johnny123");
        user.setAge(30);
        user.setEmail("johnny@gmail.com");
        user.setLastTimeLoggedIn(LocalDateTime.parse("2007-12-03T10:15:30"));
        userRepository.save(user);
        User user1 = new User();
        user1.setUsername("niko3388");
        user1.setPassword("niko12345");
        user1.setAge(33);
        user1.setEmail("niko@gmail.com");
        user1.setLastTimeLoggedIn(LocalDateTime.parse("2022-02-03T20:10:30"));
        userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("snowflake");
        user2.setPassword("snow1234");
        user2.setAge(20);
        user2.setEmail("snow@gmail.com");
        user2.setLastTimeLoggedIn(LocalDateTime.parse("2022-02-13T18:25:30"));
        userRepository.save(user2);
        User user3 = new User();
        user3.setUsername("rugby123");
        user3.setPassword("rugby123");
        user3.setAge(40);
        user3.setEmail("rugby@gmail.com");
        user3.setLastTimeLoggedIn(LocalDateTime.parse("2007-12-30T11:15:30"));
        userRepository.save(user3);
    }
}
