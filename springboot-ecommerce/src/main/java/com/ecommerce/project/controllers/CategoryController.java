package com.ecommerce.project.controllers;

import com.ecommerce.project.payloads.CategoryRequest;
import com.ecommerce.project.payloads.CategoryResponse;
import com.ecommerce.project.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getSingleCategory(@PathVariable Long categoryId) {
        CategoryResponse response = categoryService.getSingleCategory(categoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> createNewCategory(@Valid @RequestBody CategoryRequest category) {
        CategoryResponse newCategory = categoryService.createNewCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @Valid @RequestBody CategoryRequest categoryRequest,
            @PathVariable Long categoryId
    ) {
        CategoryResponse updatedCategory = categoryService.updateCategory(categoryRequest, categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated-results")
    public ResponseEntity<List<CategoryResponse>> paginatedResults(
            @RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        List<CategoryResponse> response = categoryService.getPaginatedResults(pageNumber, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
