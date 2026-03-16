package com.ecommerce.dtos;

import lombok.Data;

@Data
public class CategoryResponseDto {

    private long categoryId;
    private String categoryName;
    private String categoryDescription;
}
