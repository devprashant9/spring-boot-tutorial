package com.ecommerce.project.servicesImplementation;

import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .findFirst().orElse(null);
        categories.remove(category);
        if (category == null) {
            return "category with id: " + categoryID + " not found";
        } else {
            return "category with id: " + categoryID + " deleted";
        }
    }
}
