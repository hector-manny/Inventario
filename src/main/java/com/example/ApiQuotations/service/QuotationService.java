package com.example.ApiQuotations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ApiQuotations.model.Quotation;
import com.example.ApiQuotations.repository.QuotationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationService {
    private final QuotationRepository quotationRepository;
    
    public List<Quotation> getAllQuotations() {
        return quotationRepository.findAll();
    }

    public Quotation saveQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }
}
