package com.ecommerce.controllers;

import com.ecommerce.dtos.ApiResponse;
import com.ecommerce.dtos.CategoryResponseDto;
import com.ecommerce.dtos.ProductRequestDto;
import com.ecommerce.dtos.ProductResponseDto;
import com.ecommerce.services.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        ApiResponse<ProductResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Product Successfully created",
                productService.createProduct(productRequestDto),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        ApiResponse<ProductResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Product Successfully updated",
                productService.updateProduct(id, productRequestDto),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts() {

        ApiResponse<List<ProductResponseDto>> apiResponse = new ApiResponse<>(
                true,
                "All Products Successfully Fetched",
                productService.getAllProducts(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable Long id) {

        ApiResponse<ProductResponseDto> apiResponse = new ApiResponse<>(
                true,
                "product successfully Fetched",
                productService.getProduct(id),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProductsByCategoryId(@PathVariable Long categoryId) {
        ApiResponse<List<ProductResponseDto>> apiResponse = new ApiResponse<>(
                true,
                "Products fetched Successfully by using category",
                productService.getProductsByCategoryId(categoryId),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponse<String> apiResponse = new ApiResponse<>(
                true,
                "Category fetched Successfully",
                "Product with ID: " + id + "deleted",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
