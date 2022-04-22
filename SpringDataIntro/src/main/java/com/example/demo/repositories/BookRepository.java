package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReleaseDateAfter(LocalDate date);
    List<Book> findAllByAuthorFirstNameEqualsAndAuthorLastNameEquals(String firstName, String lastName);
}
