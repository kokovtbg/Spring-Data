package com.example.demo.services;

import com.example.demo.models.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor() throws IOException;
    List<Author> findAuthorsByBooksReleaseDateBefore();
    List<Author> findAuthorsByBooksCountDesc();
}
