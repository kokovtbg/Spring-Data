package com.example.demo.services;

import com.example.demo.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getByYearAfter();
    List<Book> getByAuthor();
}
