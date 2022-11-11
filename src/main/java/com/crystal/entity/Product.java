package com.crystal.entity;

import com.crystal.entity.request.ProductRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    public Product(ProductRequest request) {
        set(request);
    }

    public void set(ProductRequest request) {
        name = request.getName();
        description = request.getDescription();
        price = request.getPrice();
        quantity = request.getQuantity();
    }
}
