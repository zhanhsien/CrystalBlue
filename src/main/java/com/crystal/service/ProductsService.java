package com.crystal.service;

import com.crystal.entity.Product;
import com.crystal.entity.request.ProductRequest;
import com.crystal.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository repository;

    public Product create(ProductRequest request) {
        Product product = new Product(request);
        repository.save(product);
        return product;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}
