package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getByYearAfter() {
        return new ArrayList<>(bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31")));
    }

    @Override
    public List<Book> getByAuthor() {
        List<Book> allByAuthorFirstNameAndLastName =
                this.bookRepository.findAllByAuthorFirstNameEqualsAndAuthorLastNameEquals("George", "Powell");
        return allByAuthorFirstNameAndLastName.stream()
                .sorted((a1, a2) -> {
                    int result = a2.getReleaseDate().compareTo(a1.getReleaseDate());
                    if (result == 0) {
                        result = a1.getTitle().compareTo(a2.getTitle());
                    }
                    return result;
                }).collect(Collectors.toList());
    }
}
