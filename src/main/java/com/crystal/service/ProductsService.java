package com.crystal.service;

import com.crystal.entity.Product;
import com.crystal.entity.request.ProductRequest;
import com.crystal.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository repository;

    public Product create(ProductRequest request) {
        Product product = new Product(request);
        repository.save(product);
        return product;
    }

    public Product update(String id, ProductRequest request) {
        Optional<Product> optional = repository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
            product.set(request);
            repository.save(product);
        }

        return product;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}
