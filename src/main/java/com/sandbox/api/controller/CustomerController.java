package com.sandbox.api.controller;

import com.sandbox.api.model.Customer;
import com.sandbox.api.model.request.CustomerRequest;
import com.sandbox.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Customer get(@PathVariable("id") String id) {
        return customerService.get(id);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerRequest request) {
        Customer response = customerService.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@RequestBody CustomerRequest request, @PathVariable("id") String id) {
        Customer response = customerService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        customerService.delete(id);
    }
}