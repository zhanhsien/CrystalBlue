package com.crystal.init;

import com.crystal.entity.Product;
import com.crystal.entity.request.ProductRequest;
import com.crystal.repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseLoader {
    @Bean
    CommandLineRunner initDatabaase(ProductsRepository repo) {
        return args -> {
            ProductRequest crystalBlue = new ProductRequest("Crystal Blue", "100% pure", new BigDecimal(100), 1000);
            ProductRequest chilliP = new ProductRequest("Chili P", "75% pure", new BigDecimal(40), 100);
            repo.save(new Product(crystalBlue));
            repo.save(new Product(chilliP));
        };
    }
}
