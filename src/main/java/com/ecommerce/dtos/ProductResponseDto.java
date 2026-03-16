package com.ecommerce.dtos;

import lombok.Data;

@Data
public class ProductResponseDto {

    private long id;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String imageURL;
    private long categoryId;
    private String categoryName;
}
