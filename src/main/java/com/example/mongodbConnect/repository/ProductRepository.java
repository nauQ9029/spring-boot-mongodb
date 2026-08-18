/*
Text Blocks (""")

Used in @Query on ProductRepository to format complex
MongoDB JSON queries without string concatenation (+).
 */

package com.example.mongodbConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mongodbConnect.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // Java Text Block used for a clean multi-line MongoDB JSON Query
    @Query("""
            {
              'category': ?0,
              'price': { '$gte': ?1, '$lte': ?2 },
              'inStock': true
            }
            """)
    List<Product> findAvailableProductsInPriceRange(
            String category,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );
}
