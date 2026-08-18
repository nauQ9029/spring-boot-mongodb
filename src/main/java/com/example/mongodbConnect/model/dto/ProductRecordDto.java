/*
Java Records

Used for CreateProductRequest and ProductResponse DTOs.
Clean syntax, immutable, and works out-of-the-box with
Spring MVC JSON serialization.
 */
package com.example.mongodbConnect.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductRecordDto {

    // Request DTO with Jakarta Validation
    public record CreateProductRequests(
            @NotBlank(message = "Product name must not be blank")
            String name,
            @NotNull(message = "Product price must not be null")
            @Positive(message = "Product price must be a positive value")
            BigDecimal price,
            @NotBlank(message = "Product category must not be blank")
            String category
            ) {

    }

    // Response DTO
    public record ProductResponse(
            String id,
            String name,
            BigDecimal price,
            String category,
            boolean available
            ) {

    }
}
