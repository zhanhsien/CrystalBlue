package com.sandbox.api.model.request.filter;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductFilter {
    private List<String> ids;
    private String name;
    private BigDecimal price;
    @Min(0)
    private Integer stock;

    public ProductFilter(List<String> ids) {
        this.ids = ids;
    }
}
