package com.sandbox.service;

import com.sandbox.api.model.Customer;
import com.sandbox.api.model.Invoice;
import com.sandbox.api.model.Product;
import com.sandbox.api.model.request.InvoiceRequest;
import com.sandbox.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InvoiceService {

    InvoiceRepository invoiceRepository;
    ProductService productService;
    CustomerService customerService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ProductService productService, CustomerService customerService) {
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Invoice get(String id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        return optional.orElse(null);
    }

    public Invoice create(InvoiceRequest request) {
        Customer customer = customerService.get(request.getCustomerId());
        if (Objects.isNull(customer)) {
            return null;
        }

        List<Product> products = productService.getAll(request.getProductIds());
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }

        Invoice invoice = new Invoice(request);
        invoice.addProducts(products);
        customer.addInvoice(invoice);
        invoiceRepository.save(invoice);
        return invoice;
    }

    public Invoice update(InvoiceRequest request, String id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        Invoice invoice = null;
        if (optional.isPresent()) {
            invoice = optional.get();
            invoice.set(request);
            invoiceRepository.save(invoice);
        }

        return invoice;
    }

    public void delete(String id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            invoiceRepository.deleteById(id);
        }
    }
}
