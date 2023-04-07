package com.sandbox.api.controller;

import com.sandbox.api.model.Product;
import com.sandbox.api.model.request.ProductRequest;
import com.sandbox.api.model.request.filter.ProductFilter;
import com.sandbox.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(@Valid ProductFilter filter) {
        return productService.getAll(filter);
    }

    @GetMapping(value = "/{id}")
    public Product get(@PathVariable("id") String id) {
        return productService.get(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        Product response = productService.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductRequest request, @PathVariable("id") String id) {
        Product response = productService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        productService.delete(id);
    }
}