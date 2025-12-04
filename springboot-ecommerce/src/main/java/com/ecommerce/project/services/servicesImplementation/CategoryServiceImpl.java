package com.ecommerce.project.services.servicesImplementation;

import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel getSingleCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Category not found with id = " + categoryId));
    }

    @Override
    public void createNewCategory(CategoryModel category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {
        CategoryModel category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
        categoryRepository.delete(category);
        return "category with id: " + categoryID + " deleted";
    }

    @Override
    public CategoryModel updateCategory(CategoryModel category, Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
        CategoryModel savedCategory;

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
}
