package com.mka.rest.repositories.specifications;

import com.mka.rest.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer cost) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> costLessOrEqualsThan(Integer cost) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
