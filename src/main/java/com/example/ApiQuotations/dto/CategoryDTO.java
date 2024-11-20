package com.example.ApiQuotations.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private BigDecimal freightPercentage;
    private BigDecimal importExpensesPercentage;
    private BigDecimal insurancePercentage;
    private BigDecimal taxPercentage;
}
