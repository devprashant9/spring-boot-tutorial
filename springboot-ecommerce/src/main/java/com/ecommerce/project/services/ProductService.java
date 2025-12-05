package com.ecommerce.project.services;

import com.ecommerce.project.payloads.RequestDTOs.ProductRequest;
import com.ecommerce.project.payloads.ResponseDTOs.ProductResponse;
import com.ecommerce.project.payloads.common.PaginatedResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getProducts();
    ProductResponse getProduct(Long productId);
    ProductResponse createProduct(ProductRequest productRequest, Long categoryId);
    ProductResponse updateProduct(ProductRequest productRequest, Long productId, Long categoryId);
    void deleteProduct(Long productId);
    PaginatedResponse<ProductResponse> getPaginatedProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    List<ProductResponse> getProductsByCategory(Long categoryId);
    List<ProductResponse> getProductsByKeyword(String keyword);
}
