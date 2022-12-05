package com.mka.rest.controllers;

import com.mka.rest.converters.ProductConverter;
import com.mka.rest.dto.ProductDto;
import com.mka.rest.entities.Product;
import com.mka.rest.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping()
    public Page<ProductDto> getProductsPage(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minCost, maxCost, titlePart, page).map(
                productConverter::entityToDto
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукта нет в базе");
        return productConverter.entityToDto(product);
    }

    @PostMapping()
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
