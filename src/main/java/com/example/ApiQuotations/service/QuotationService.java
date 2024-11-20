package com.example.ApiQuotations.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ApiQuotations.exception.InvalidDataException;
import com.example.ApiQuotations.exception.NotFoundException;
import com.example.ApiQuotations.model.Customer;
import com.example.ApiQuotations.model.Product;
import com.example.ApiQuotations.model.Quotation;
import com.example.ApiQuotations.repository.CustomerRepository;
import com.example.ApiQuotations.repository.QuotationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final ProductService productService;
    private final CustomerRepository customerRepository;


    

public Map<String, Object> saveQuotation(Quotation quotation, Long productId) {
    // Validar producto existente
    Product product = productService.getProductById(productId);
    
    // Validar datos del producto
    if (product.getPrice() == null || product.getWeight() == null) {
        throw new InvalidDataException("El producto debe tener un precio y peso válidos.");
    }

    // Obtener porcentajes desde la categoría asociada
    if (product.getCategory() == null) {
        throw new InvalidDataException("El producto debe tener una categoría asignada.");
    }

    BigDecimal freightPercentage = product.getCategory().getFreightPercentage();
    BigDecimal importExpensesPercentage = product.getCategory().getImportExpensesPercentage();
    BigDecimal insurancePercentage = product.getCategory().getInsurancePercentage();
    BigDecimal taxPercentage = product.getCategory().getTaxPercentage();

    // Cálculo de costos
    BigDecimal freight = product.getPrice().multiply(freightPercentage);
    BigDecimal importExpenses = product.getPrice().multiply(importExpensesPercentage);
    BigDecimal insurance = product.getPrice().multiply(insurancePercentage);
    BigDecimal tax = product.getPrice().multiply(taxPercentage);

    BigDecimal totalImportCost = freight.add(importExpenses).add(insurance).add(tax);
    BigDecimal totalProductAndImportCost = product.getPrice().add(totalImportCost);

    // Asignar valores calculados a la cotización
    quotation.setTotalImportCost(totalImportCost);
    quotation.setTotalProductAndImportCost(totalProductAndImportCost);
    quotation.setCreatedAt(LocalDateTime.now());

    // Buscar cliente relacionado con el usuario creador del producto
    Customer customer = customerRepository.findByUserId(product.getCreatedBy().getId().longValue())
            .orElseThrow(() -> new NotFoundException("Cliente no encontrado."));
    quotation.setCustomer(customer);

    // Guardar cotización
    quotationRepository.save(quotation);

    // Crear un Map mutable para la respuesta
    Map<String, Object> response = new HashMap<>();
    response.put("quotationId", quotation.getId());
    response.put("customerName", customer.getFullName());
    response.put("email", customer.getUser().getEmail());
    response.put("productType", product.getName());
    response.put("weight", product.getWeight());
    response.put("cost", product.getPrice());
    response.put("freight", freight);
    response.put("importExpenses", importExpenses);
    response.put("insurance", insurance);
    response.put("tax", tax);
    response.put("totalImportCost", totalImportCost);
    response.put("totalCost", totalProductAndImportCost);

    return response;
}


    public List<Quotation> getAllQuotations() {
        return quotationRepository.findAll();
    }

    public Map<String, Object> getQuotationDetailsById(Integer id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cotización no encontrada."));
    
        Customer customer = quotation.getCustomer();
    
        // Formar la respuesta
        return Map.of(
                "quotationId", quotation.getId(),
                "customerName", customer.getFullName(),
                "email", customer.getUser().getEmail(),
                "totalImportCost", quotation.getTotalImportCost(),
                "totalCost", quotation.getTotalProductAndImportCost()
        );
    }
    
}
