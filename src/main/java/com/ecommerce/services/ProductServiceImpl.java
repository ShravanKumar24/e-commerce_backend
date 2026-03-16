package com.ecommerce.services;

import com.ecommerce.dtos.ProductRequestDto;
import com.ecommerce.dtos.ProductResponseDto;
import com.ecommerce.entites.Category;
import com.ecommerce.entites.Product;
import com.ecommerce.errorhandlers.ResourceNotFoundException;
import com.ecommerce.repositories.CategoryRepo;
import com.ecommerce.repositories.ProductRepo;
import com.ecommerce.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepo.findById(productRequestDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        Product product = new Product();
        product.setProductName(productRequestDto.getProductName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setImageUrl(productRequestDto.getImageURL());
        product.setCategory(category);
        return mapToResponse(productRepo.save(product));
    }


    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        Category category = categoryRepo.findById(productRequestDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        product.setProductName(productRequestDto.getProductName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setImageUrl(productRequestDto.getImageURL());
        product.setCategory(category);
        return mapToResponse(productRepo.save(product));
    }

    @Override
    public void deleteProduct(long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        productRepo.delete(product);
    }

    @Override
    public ProductResponseDto getProduct(long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryId(long categoryId) {

        return productRepo.findByCategoryId(categoryId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private ProductResponseDto mapToResponse(Product product) {
        ProductResponseDto responseDto = modelMapper.map(product, ProductResponseDto.class);
        responseDto.setCategoryId(product.getCategory().getId());
        responseDto.setCategoryName(product.getCategory().getCategoryName());
        return responseDto;


    }
}
