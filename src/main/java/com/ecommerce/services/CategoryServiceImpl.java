package com.ecommerce.services;

import com.ecommerce.dtos.CategoryRequestDto;
import com.ecommerce.dtos.CategoryResponseDto;
import com.ecommerce.entites.Category;
import com.ecommerce.errorhandlers.DuplicateResourceException;
import com.ecommerce.errorhandlers.ResourceNotFoundException;
import com.ecommerce.repositories.CategoryRepo;
import com.ecommerce.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryResponseDto creeateCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRepo.findByCategoryName(categoryRequestDto.getCategoryName()).isPresent()) {
           throw  new DuplicateResourceException("Category already exists");
        }
        Category category = modelMapper.map(categoryRequestDto, Category.class);
        return modelMapper.map(categoryRepo.save(category), CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setDescription(categoryRequestDto.getDescription());
        return modelMapper.map(categoryRepo.save(category), CategoryResponseDto.class);
    }

    @Override
    public void deleteCategoryById(long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepo.delete(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(category ->
                modelMapper.map(category, CategoryResponseDto.class)
        ).collect(Collectors.toList());
    }
}
