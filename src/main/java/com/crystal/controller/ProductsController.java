package com.crystal.controller;

import com.crystal.entity.Product;
import com.crystal.entity.request.ProductRequest;
import com.crystal.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService service;

    //create product
    @RequestMapping(value = "/products", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<Product> createProducts(@RequestBody ProductRequest request) {
        Product response = service.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }
    //get product
    @RequestMapping(value = "/products", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listProduct() {
        return ResponseEntity.ok(service.getAll());
    }
    //update product
    @RequestMapping(value = "/products/{id}", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProducts(@RequestBody ProductRequest request, @PathVariable("id") String id) {
        Product response = service.update(request, id);
        return ResponseEntity.ok(response);
    }

}