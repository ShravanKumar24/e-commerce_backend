package com.ecommerce.services.interfaces;

import com.ecommerce.dtos.ProductRequestDto;
import com.ecommerce.dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProduct(long productId);

    ProductResponseDto getProduct(long productId);

    List<ProductResponseDto> getProductsByCategoryId(long categoryId);

    List<ProductResponseDto> getAllProducts();
}
