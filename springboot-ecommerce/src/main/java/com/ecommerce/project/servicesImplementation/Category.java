package com.ecommerce.project.servicesImplementation;

import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Category implements CategoryService {

    private final List<CategoryModel> categories = new ArrayList<>();
    private Long nextID = 1L;

    @Override
    public List<CategoryModel> getAllCategories() {
        return categories;
    }

    @Override
    public void createNewCategory(CategoryModel category) {
        category.setCategoryId(nextID++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {
        CategoryModel category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryID))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
        categories.remove(category);
        return "category with id: " + categoryID + " deleted";
    }

    @Override
    public CategoryModel updateCategory(CategoryModel category, Long categoryId) {
        Optional<CategoryModel> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if(optionalCategory.isPresent()) {
            CategoryModel existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found with given id");
        }
    }
}
