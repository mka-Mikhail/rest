package com.mka.rest.controllers;

import com.mka.rest.models.Product;
import com.mka.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/app/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/app/products")
    public void saveNewProduct(@RequestBody Product product) {
        productService.addNewProductToRepo(product);
    }

    @GetMapping("/app/products/delete/{id}")
    public void deleteProductFromRepoById(@PathVariable Long id) {
        productService.deleteProductFromRepoById(id);
    }
}
