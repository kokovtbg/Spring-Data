package com.example.demo.services;

import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() throws IOException {
        long size = this.authorRepository.count();
        int authorId = new Random().nextInt((int) size) + 1;
        return this.authorRepository.findById(authorId).get();
    }

    @Override
    public List<Author> findAuthorsByBooksReleaseDateBefore() {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(LocalDate.parse("1990-01-01"));
    }

    @Override
    public List<Author> findAuthorsByBooksCountDesc() {
        List<Author> allAuthors = this.authorRepository.findAll();
        return allAuthors.stream()
                .sorted((a1, a2) -> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
                .collect(Collectors.toList());
    }

}
