package com.sandbox.api.model;

import com.sandbox.api.model.request.CustomerRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @Column(name = "create_gmts")
    private Timestamp createGmts;

    @Column(name = "update_gmts")
    private Timestamp updateGmts;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    @JsonManagedReference
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Customer(CustomerRequest request) {
        set(request, true);
    }

    public void set(CustomerRequest request) {
        set(request, false);
    }

    public void set(CustomerRequest request, boolean create) {
        name = request.getName();
        address = request.getAddress();
        phone = request.getPhone();
        email = request.getEmail();
        updateGmts = new Timestamp(System.currentTimeMillis());

        if (create) {
            createGmts = new Timestamp(System.currentTimeMillis());
        }
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setCustomer(this);
    }
}
