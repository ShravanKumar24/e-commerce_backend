package com.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "Category name is required")
    private String categoryName;

    @NotBlank(message = "Description cannot be Blank")
     private String description;
}
