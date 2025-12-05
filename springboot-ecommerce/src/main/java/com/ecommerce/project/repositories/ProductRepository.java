package com.ecommerce.project.repositories;

import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByCategoryModel(CategoryModel categoryModel);
    List<ProductModel> findByProductNameLikeIgnoreCase(String keyword);
}
