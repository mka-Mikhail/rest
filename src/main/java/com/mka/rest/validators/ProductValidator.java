package com.mka.rest.validators;

import com.mka.rest.dto.ProductDto;
import com.mka.rest.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getCost() < 1) {
            errors.add("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Название продукта не может быть пустым");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
