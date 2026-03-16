package com.ecommerce.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Product Name is required")
    private String productName;

    @NotBlank(message = "Product Description is required")
    private String description;

    @NotNull
    @DecimalMin(value = "0.1", message = "Price must be Greater than 0")
    private double price;

    @NotNull
    @Min(value = 0, message = "stock quantity cannot be negative")
    private int quantity;

    @NotNull(message = "Category id is required")
    private long categoryId;

    @NotNull(message = "Image is required")
    private String imageURL;
}
