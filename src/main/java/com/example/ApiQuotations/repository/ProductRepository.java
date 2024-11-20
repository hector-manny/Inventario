package com.example.ApiQuotations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiQuotations.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    
} 

