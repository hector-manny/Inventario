package com.example.ApiQuotations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiQuotations.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
