package com.mka.rest.services;

import com.mka.rest.models.IProductRepository;
import com.mka.rest.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Product> getProducts() {
        Iterable<Product> source = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        source.forEach(products::add);
        return products;
    }

    public void addNewProductToRepo(Product product) {
        productRepository.save(product);
    }

    public void deleteProductFromRepoById(Long id) {
        productRepository.deleteById(id);
    }
}
