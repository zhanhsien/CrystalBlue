package com.sandbox.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class InvoiceRequest {
    private String customerId;
    private List<String> productIds;
}
