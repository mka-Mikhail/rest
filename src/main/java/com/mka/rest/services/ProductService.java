package com.mka.rest.services;

import com.mka.rest.dto.ProductDto;
import com.mka.rest.exceptions.ResourceNotFoundException;
import com.mka.rest.repositories.IProductRepository;
import com.mka.rest.entities.Product;
import com.mka.rest.repositories.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public Page<Product> findAll(Integer minCost, Integer maxCost, String titlePart, Integer page) {
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

//    public List<Product> getProducts() {
//        Iterable<Product> source = productRepository.findAll();
//        List<Product> products = new ArrayList<>();
//        source.forEach(products::add);
//        return products;
//    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Невозможно обновить, продукт не найден в базе, id: " + productDto.getId())
        );
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        return product;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
