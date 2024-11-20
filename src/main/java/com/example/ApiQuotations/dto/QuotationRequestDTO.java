package com.example.ApiQuotations.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationRequestDTO {
    private String customerName;
    private String email;
    private Long productTypeId;
    private BigDecimal weight;
    private BigDecimal productCost;
}
