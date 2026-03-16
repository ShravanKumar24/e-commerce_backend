package com.ecommerce.controllers;

import com.ecommerce.dtos.ApiResponse;
import com.ecommerce.dtos.CategoryRequestDto;
import com.ecommerce.dtos.CategoryResponseDto;
import com.ecommerce.services.interfaces.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        ApiResponse<CategoryResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Category created Successfully",
                categoryService.creeateCategory(categoryRequestDto),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        ApiResponse<CategoryResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Category updated Successfully",
                categoryService.updateCategory(id, categoryRequestDto),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories() {

        ApiResponse<List<CategoryResponseDto>> apiResponse = new ApiResponse<>(
                true,
                "Categories Successfully fetched",
                categoryService.getAllCategories(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        ApiResponse<CategoryResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Category fetched Successfully",
                categoryService.getCategoryById(id),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategoryById(@PathVariable Long id) {

        categoryService.deleteCategoryById(id);
        ApiResponse<String> apiResponse = new ApiResponse<>(
                true,
                "Category deleted Successfully",
                "Category with ID : " + id + "deleted",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
