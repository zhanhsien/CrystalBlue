package com.sandbox.service;

import com.sandbox.api.model.Customer;
import com.sandbox.api.model.request.CustomerRequest;
import com.sandbox.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer get(String id) {
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElse(null);
    }

    public Customer create(CustomerRequest request) {
        Customer customer = new Customer(request);
        customerRepository.save(customer);
        return customer;
    }

    public Customer update(CustomerRequest request, String id) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
            customer.set(request);
            customerRepository.save(customer);
        }

        return customer;
    }

    public void delete(String id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            customerRepository.deleteById(id);
        }
    }
}
