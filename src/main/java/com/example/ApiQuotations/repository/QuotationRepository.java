package com.example.ApiQuotations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiQuotations.model.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
}
