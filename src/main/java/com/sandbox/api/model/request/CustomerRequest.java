package com.sandbox.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
}
