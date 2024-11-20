package com.example.ApiQuotations.service;

import com.example.ApiQuotations.model.Customer;
import com.example.ApiQuotations.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setFullName(updatedCustomer.getFullName());
        existingCustomer.setDocumentNumber(updatedCustomer.getDocumentNumber());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setCountry(updatedCustomer.getCountry());
        existingCustomer.setState(updatedCustomer.getState());
        existingCustomer.setCity(updatedCustomer.getCity());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
