package com.ecommerce.project.services;

import com.ecommerce.project.models.CategoryModel;

import java.util.List;

public interface CategoryService {

    List<CategoryModel> getAllCategories();
    CategoryModel getSingleCategory(Long categoryId);
    void createNewCategory(CategoryModel category);
    String deleteCategory(Long categoryID);
    CategoryModel updateCategory(CategoryModel category, Long categoryId);
}
