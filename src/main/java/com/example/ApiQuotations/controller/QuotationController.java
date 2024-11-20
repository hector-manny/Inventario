package com.example.ApiQuotations.controller;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiQuotations.service.QuotationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
public class QuotationController {
    private QuotationService quotationService;
    

    @GetMapping
    public ResponseEntity<List<com.example.ApiQuotations.model.Quotation>> getAllQuotations() {
        return ResponseEntity.ok(quotationService.getAllQuotations());
    }

    @PostMapping
    public ResponseEntity<com.example.ApiQuotations.model.Quotation> createQuotation(@RequestBody com.example.ApiQuotations.model.Quotation request) {
        return new ResponseEntity<>(quotationService.saveQuotation(request), HttpStatus.CREATED);
    }
}
