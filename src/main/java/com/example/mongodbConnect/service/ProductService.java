/*
Streams API Enhancements

Used in ProductService.getCatalog() to cleanly transform the
List<Product> returned from MongoDB into List<ProductResponse>.
 */

package com.example.mongodbConnect.service;

import com.example.mongodbConnect.model.dto.ProductRecordDto;
import com.example.mongodbConnect.model.entity.Product;
import com.example.mongodbConnect.repository.ProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    // Constructor injection
    public ProductService(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
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
            Query query = new Query().addCriteria(Criteria.where("inStock").is(true));
            if (category != null) {
                query.addCriteria(Criteria.where("category")
                        .regex("^" + Pattern.quote(category) + "$", "i"));
            }
            if (minPrice != null || maxPrice != null) {
                Criteria priceCriteria = Criteria.where("price");
                if (minPrice != null) {
                    priceCriteria = priceCriteria.gte(minPrice);
                }
                if (maxPrice != null) {
                    priceCriteria = priceCriteria.lte(maxPrice);
                }
                query.addCriteria(priceCriteria);
            }
            products = mongoTemplate.find(query, Product.class);
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
