package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() throws IOException {
        long size = this.categoryRepository.count();
        Random random = new Random();

        int categoriesCount = random.nextInt((int) size) + 1;
        Set<Integer> categoriesIds = new HashSet<>();
        for (int i = 0; i < categoriesCount; i++) {
            int nextId = random.nextInt((int) size) + 1;
            categoriesIds.add(nextId);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesIds);
        return new HashSet<>(allById);
    }
}
