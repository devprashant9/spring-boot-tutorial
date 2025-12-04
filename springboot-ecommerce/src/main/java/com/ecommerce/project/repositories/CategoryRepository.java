package com.ecommerce.project.repositories;

import com.ecommerce.project.models.CategoryModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    CategoryModel findByCategoryName(String categoryName);
}
