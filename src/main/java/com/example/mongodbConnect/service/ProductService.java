/*
Streams API Enhancements

Used in ProductService.getCatalog() to cleanly transform the
List<Product> returned from MongoDB into List<ProductResponse>.
 */

package com.example.mongodbConnect.service;

import com.example.mongodbConnect.model.dto.ProductRecordDto;
import com.example.mongodbConnect.model.entity.Product;
import com.example.mongodbConnect.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRecordDto.ProductResponse createProduct(ProductRecordDto.CreateProductRequests request) {
        Product product = new Product(
                request.name(),
                request.price(),
                request.category(),
                true
        );

        Product saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    public List<ProductRecordDto.ProductResponse> getProducts(String category, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products;

        if (category == null && minPrice == null && maxPrice == null) {
            products = productRepository.findAll();
        } else if (category != null && minPrice != null && maxPrice != null) {
            products = productRepository.findAvailableProductsInPriceRange(category, minPrice, maxPrice);
        } else {
            products = productRepository.findAll().stream()
                    .filter(product -> product.isInStock())
                    .filter(product -> category == null || product.getCategory().equalsIgnoreCase(category))
                    .filter(product -> minPrice == null || product.getPrice().compareTo(minPrice) >= 0)
                    .filter(product -> maxPrice == null || product.getPrice().compareTo(maxPrice) <= 0)
                    .toList();
        }

        return products.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Helper mapper method
    private ProductRecordDto.ProductResponse mapToResponse(Product product) {
        return new ProductRecordDto.ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.isInStock()
        );
    }
}
