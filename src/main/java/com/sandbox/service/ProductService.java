package com.sandbox.service;

import com.sandbox.api.model.Product;
import com.sandbox.api.model.request.ProductRequest;
import com.sandbox.api.model.request.filter.ProductFilter;
import com.sandbox.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private List<Product> filterProducts(ProductFilter filter, List<Product> products) {
        return products.stream().filter((product) ->
            (StringUtils.isBlank(filter.getName()) || product.getName().toLowerCase().startsWith(filter.getName().toLowerCase()))
            && (CollectionUtils.isEmpty(filter.getIds()) || filter.getIds().contains(product.getId()))
        ).collect(Collectors.toList());
    }

    public List<Product> getAll(List<String> ids) {
        return getAll(new ProductFilter(ids));
    }

    public List<Product> getAll(ProductFilter filter) {
        List<Product> products = productRepository.findAll();
        if (Objects.isNull(filter.getName()) && CollectionUtils.isEmpty(filter.getIds())) {
            return products;
        }

        return filterProducts(filter, products);
    }

    public Product get(String id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    public Product create(ProductRequest request) {
        Product product = new Product(request);
        productRepository.save(product);
        return product;
    }

    public Product update(ProductRequest request, String id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
            product.set(request);
            productRepository.save(product);
        }

        return product;
    }

    public void delete(String id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.deleteById(id);
        }
    }
}
