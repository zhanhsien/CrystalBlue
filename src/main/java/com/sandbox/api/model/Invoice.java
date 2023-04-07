package com.sandbox.api.model;

import com.sandbox.api.model.request.InvoiceRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "INVOICE")
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private BigDecimal price;

    @Column(name = "create_gmts")
    private Timestamp createGmts;

    @Column(name = "update_gmts")
    private Timestamp updateGmts;

    @Getter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "INVOICE_PRODUCT",
            joinColumns = { @JoinColumn(name = "invoice_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference
    public List<Product> getProducts() {
        return products;
    }

    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }

    public Invoice(InvoiceRequest request) {
        set(request, true);
    }

    public void set(InvoiceRequest request) {
        set(request, false);
    }

    public void set(InvoiceRequest request, boolean create) {
        updateGmts = new Timestamp(System.currentTimeMillis());

        if (create) {
            createGmts = new Timestamp(System.currentTimeMillis());
        }
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
        price = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        products.forEach((product) -> product.addInvoice(this));
    }
}
