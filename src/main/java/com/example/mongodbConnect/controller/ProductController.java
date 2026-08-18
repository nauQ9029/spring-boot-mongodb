package com.example.mongodbConnect.controller;

import com.example.mongodbConnect.model.dto.ProductRecordDto;
import com.example.mongodbConnect.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductRecordDto.ProductResponse> createProduct(
            @Valid @RequestBody ProductRecordDto.CreateProductRequests request) {
        ProductRecordDto.ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<ProductRecordDto.ProductResponse> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return productService.getProducts(category, minPrice, maxPrice);
    }
}
