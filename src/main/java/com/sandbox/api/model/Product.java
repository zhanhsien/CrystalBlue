package com.sandbox.api.model;

import com.sandbox.api.model.request.ProductRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    @Column(name = "create_gmts")
    private Timestamp createGmts;

    @Column(name = "update_gmts")
    private Timestamp updateGmts;

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    @JsonBackReference
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Product(ProductRequest request) {
        set(request, true);
    }

    public void set(ProductRequest request) {
        set(request, false);
    }

    public void set(ProductRequest request, boolean create) {
        name = request.getName();
        description = request.getDescription();
        price = request.getPrice();
        stock = request.getStock();
        updateGmts = new Timestamp(System.currentTimeMillis());

        if (create) {
            createGmts = new Timestamp(System.currentTimeMillis());
        }
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }
}
