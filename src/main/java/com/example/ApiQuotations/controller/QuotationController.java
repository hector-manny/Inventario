package com.example.ApiQuotations.controller;

import com.example.ApiQuotations.model.Quotation;
import com.example.ApiQuotations.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quotations")
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;

   
    @PostMapping
    public ResponseEntity<Map<String, Object>> createQuotation(
            @RequestParam Long productId,
            @RequestBody Quotation quotation) {
        Map<String, Object> response = quotationService.saveQuotation(quotation, productId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Quotation>> getAllQuotations() {
        List<Quotation> quotations = quotationService.getAllQuotations();
        return ResponseEntity.ok(quotations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getQuotationById(@PathVariable Integer id) {
        Map<String, Object> response = quotationService.getQuotationDetailsById(id);
        return ResponseEntity.ok(response);
    }
}
