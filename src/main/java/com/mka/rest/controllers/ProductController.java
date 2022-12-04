package com.mka.rest.controllers;

import com.mka.rest.models.Product;
import com.mka.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
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

    @PostMapping()
    public void saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        productService.save(product);
    }

    @PutMapping()
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductFromRepoById(@PathVariable Long id) {
        productService.deleteProductFromRepoById(id);
    }
}
