package com.mka.rest.services;

import com.mka.rest.repositories.IProductRepository;
import com.mka.rest.models.Product;
import com.mka.rest.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Integer minCost, Integer maxCost, String titlePart, Integer page) {
        Specification<Product> specification = Specification.where(null);
        if (minCost != null) {
            specification = specification.and(ProductSpecifications.costGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            specification = specification.and(ProductSpecifications.costLessOrEqualsThan(maxCost));
        }
        if (titlePart != null) {
            specification = specification.and(ProductSpecifications.titleLike(titlePart));
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 5));
    }

    public List<Product> getProducts() {
        Iterable<Product> source = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        source.forEach(products::add);
        return products;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProductFromRepoById(Long id) {
        productRepository.deleteById(id);
    }
}
