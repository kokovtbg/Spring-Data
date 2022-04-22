package spring.advQuerying.service;

import spring.advQuerying.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<Author> findAllByFirstNameEndingWith(String part);

    List<Author> findAll();
}
