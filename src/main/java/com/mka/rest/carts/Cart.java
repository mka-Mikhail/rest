package com.mka.rest.carts;

import com.mka.rest.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Data
@AllArgsConstructor
public class Cart {
    private List<Product> products;

    public void save(Product product) {
        products.add(product);
    }

    public void deleteById(Long id) {
        Product product = products.stream()
                .filter(it -> Objects.equals(id, it.getId()))
                .findFirst()
                .orElse(null);
        products.remove(product);
    }
}
