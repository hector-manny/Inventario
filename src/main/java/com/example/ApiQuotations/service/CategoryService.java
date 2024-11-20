package com.example.ApiQuotations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ApiQuotations.model.Category;
import com.example.ApiQuotations.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category categoryDTO) {
        return categoryRepository.save(categoryDTO);
    }
}
