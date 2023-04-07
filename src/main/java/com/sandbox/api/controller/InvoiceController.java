package com.sandbox.api.controller;

import com.sandbox.api.model.Invoice;
import com.sandbox.api.model.request.InvoiceRequest;
import com.sandbox.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/invoice")
@CrossOrigin(origins = "http://localhost:5173")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Invoice get(@PathVariable("id") String id) {
        return invoiceService.get(id);
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoiceRequest request) {
        Invoice response = invoiceService.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Invoice> update(@RequestBody InvoiceRequest request, @PathVariable("id") String id) {
        Invoice response = invoiceService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        invoiceService.delete(id);
    }
}