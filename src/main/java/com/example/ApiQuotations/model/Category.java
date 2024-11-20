package com.example.ApiQuotations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(name = "freight_percentage")
    private BigDecimal freightPercentage;
    
    @Column(name = "import_expenses_percentage")
    private BigDecimal importExpensesPercentage;
    
    @Column(name = "insurance_percentage")
    private BigDecimal insurancePercentage;
    
    @Column(name = "tax_percentage")
    private BigDecimal taxPercentage;
}