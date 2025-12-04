package com.ecommerce.project.services;

import com.ecommerce.project.payloads.CategoryRequest;
import com.ecommerce.project.payloads.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();
    CategoryResponse getSingleCategory(Long categoryId);
    CategoryResponse createNewCategory(CategoryRequest category);
    CategoryResponse updateCategory(CategoryRequest category, Long categoryId);
    void deleteCategory(Long categoryId);
    List<CategoryResponse> getPaginatedResults(Integer pageNumber, Integer pageSize);
}

