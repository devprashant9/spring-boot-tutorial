package com.ecommerce.project.controllers;

import com.ecommerce.project.configs.AppConstants;
import com.ecommerce.project.payloads.RequestDTOs.CategoryRequest;
import com.ecommerce.project.payloads.ResponseDTOs.CategoryResponse;
import com.ecommerce.project.payloads.common.PaginatedResponse;
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
    public ResponseEntity<PaginatedResponse<CategoryResponse>> paginatedResults(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY_CATEGORIES) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder
    ) {
        PaginatedResponse<CategoryResponse> response = categoryService.getPaginatedResults(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
