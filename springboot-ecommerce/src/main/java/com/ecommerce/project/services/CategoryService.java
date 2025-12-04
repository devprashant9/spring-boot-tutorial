package com.ecommerce.project.services;

import com.ecommerce.project.payloads.RequestDTOs.CategoryRequest;
import com.ecommerce.project.payloads.ResponseDTOs.CategoryResponse;
import com.ecommerce.project.payloads.common.PaginatedResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();
    CategoryResponse getSingleCategory(Long categoryId);
    CategoryResponse createNewCategory(CategoryRequest category);
    CategoryResponse updateCategory(CategoryRequest category, Long categoryId);
    void deleteCategory(Long categoryId);
    PaginatedResponse<CategoryResponse> getPaginatedResults(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}

