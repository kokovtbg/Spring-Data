package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByEmailEndingWith(String provider);
    List<User> findAllByLastTimeLoggedInBefore(LocalDateTime dateTime);
    void deleteAllByIsDeletedTrue();
}
