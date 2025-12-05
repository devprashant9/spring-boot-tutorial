package com.ecommerce.project.controllers;

import com.ecommerce.project.configs.AppConstants;
import com.ecommerce.project.payloads.RequestDTOs.ProductRequest;
import com.ecommerce.project.payloads.ResponseDTOs.ProductResponse;
import com.ecommerce.project.payloads.common.PaginatedResponse;
import com.ecommerce.project.services.servicesImplementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> response = productService.getProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse response = productService.getProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest, @PathVariable Long categoryId) {
        ProductResponse response = productService.createProduct(productRequest, categoryId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}/{categoryId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest,
            @PathVariable Long productId,
            @PathVariable Long categoryId
            ) {
        ProductResponse response = productService.updateProduct(productRequest, productId, categoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated-results")
    public ResponseEntity<PaginatedResponse<ProductResponse>> getPaginatedProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY_PRODUCTS) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder
    ) {

        PaginatedResponse<ProductResponse> response = productService.getPaginatedProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductResponse> response = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<ProductResponse>> getProductsByKeywords(@PathVariable String keyword) {
        List<ProductResponse> response = productService.getProductsByKeyword(keyword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
