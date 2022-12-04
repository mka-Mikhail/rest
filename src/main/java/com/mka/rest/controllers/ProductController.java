package com.mka.rest.controllers;

import com.mka.rest.models.Product;
import com.mka.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/app/products")
    public Page<Product> getProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minCost, maxCost, titlePart, page);
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
