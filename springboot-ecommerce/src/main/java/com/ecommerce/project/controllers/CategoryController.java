package com.ecommerce.project.controllers;

import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryModel> getSingleCategory(@PathVariable Long categoryId) {
        CategoryModel categoryModel = categoryService.getSingleCategory(categoryId);
        return ResponseEntity.ok(categoryModel);
    }

    @PostMapping()
    public ResponseEntity<String> createNewCategory(@RequestBody CategoryModel category) {
        categoryService.createNewCategory(category);
        return new ResponseEntity<>("category created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryModel category, @PathVariable Long categoryId) {
        try {
            CategoryModel updatedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("category with id: " + categoryId + " updated", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
