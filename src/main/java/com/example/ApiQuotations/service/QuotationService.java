package com.example.ApiQuotations.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.ApiQuotations.dto.QuotationRequestDTO;
import com.example.ApiQuotations.model.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationService {
    private final QuotationRepository quotationRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    
    public QuotationResponse createQuotation(QuotationRequestDTO request) {
        Category category = categoryRepository.findById(request.getProductTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        // Calculate costs
        BigDecimal freight = calculatePercentage(request.getProductCost(), category.getFreightPercentage());
        BigDecimal importExpenses = calculatePercentage(request.getProductCost(), category.getImportExpensesPercentage());
        BigDecimal insurance = calculatePercentage(request.getProductCost(), category.getInsurancePercentage());
        BigDecimal taxes = calculatePercentage(request.getProductCost(), category.getTaxPercentage());
        
        BigDecimal totalImportCost = freight.add(importExpenses).add(insurance).add(taxes);
        BigDecimal totalCost = totalImportCost.add(request.getProductCost());
        
        // Create quotation
        Quotation quotation = new Quotation();
        // Set quotation properties
        
        quotation = quotationRepository.save(quotation);
        
        // Send email notification (optional)
        emailService.sendQuotationNotification(request.getEmail(), quotation);
        
        return mapToQuotationResponse(quotation);
    }
    
    private BigDecimal calculatePercentage(BigDecimal base, BigDecimal percentage) {
        return base.multiply(percentage).divide(BigDecimal.valueOf(100));
    }
}
