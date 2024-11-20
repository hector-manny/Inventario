package com.example.ApiQuotations.controller;

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

import com.example.ApiQuotations.dto.QuotationRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
public class QuotationController {
    private final QuotationService quotationService;
    
    @PostMapping
    public ResponseEntity<QuotationResponse> createQuotation(@RequestBody QuotationRequestDTO request) {
        return new ResponseEntity<>(quotationService.createQuotation(request), HttpStatus.CREATED);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<QuotationResponse>> getAllQuotations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        return ResponseEntity.ok(quotationService.getAllQuotations(page, size, sortBy, sortDir));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<QuotationResponse> getQuotationById(@PathVariable Long id) {
        return ResponseEntity.ok(quotationService.getQuotationById(id));
    }
}
