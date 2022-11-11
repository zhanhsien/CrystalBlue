package com.crystal.controller;

import com.crystal.entity.Product;
import com.crystal.entity.request.ProductRequest;
import com.crystal.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService service;

    @RequestMapping(value = "/products", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Product response = service.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }

    @RequestMapping(value = "/products/{id}", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
    public ResponseEntity<Product> createProduct(@PathVariable("id") String id, @RequestBody ProductRequest request) {
        Product response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/products", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(service.getAll());
    }
}
