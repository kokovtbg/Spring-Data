package com.example.demo.services;

import com.example.demo.models.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories() throws IOException;
}
