package com.mka.rest.dto;

import com.mka.rest.models.Product;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String title;
    private Integer cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }
}
