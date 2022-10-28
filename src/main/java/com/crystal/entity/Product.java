package com.crystal.entity;

import com.crystal.entity.request.ProductRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Product {
    private @Id @GeneratedValue long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    public Product(String name, String description, BigDecimal price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(ProductRequest request) {
        name = request.getName();
        description = request.getDescription();
        price = request.getPrice();
        quantity = request.getQuantity();
    }
}
