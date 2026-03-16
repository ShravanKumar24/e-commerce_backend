package com.ecommerce.services.interfaces;

import com.ecommerce.dtos.CategoryRequestDto;
import com.ecommerce.dtos.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto creeateCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto getCategoryById(Long categoryId);

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);

    void deleteCategoryById(long categoryId);

    List<CategoryResponseDto> getAllCategories();

}
