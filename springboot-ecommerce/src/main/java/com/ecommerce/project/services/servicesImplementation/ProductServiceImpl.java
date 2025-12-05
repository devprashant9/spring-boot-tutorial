package com.ecommerce.project.services.servicesImplementation;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.models.ProductModel;
import com.ecommerce.project.payloads.RequestDTOs.ProductRequest;
import com.ecommerce.project.payloads.ResponseDTOs.ProductResponse;
import com.ecommerce.project.payloads.common.PaginatedResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import com.ecommerce.project.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getProducts() {
        List<ProductModel> allProducts = productRepository.findAll();
        if(allProducts.isEmpty()) {
            throw new APIException("No products available");
        }
        return allProducts.stream().map(product -> modelMapper.map(product, ProductResponse.class)).toList();
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        ProductModel productModel = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        return modelMapper.map(productModel, ProductResponse.class);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, Long categoryId) {

        CategoryModel categoryModel = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        // Build product manually
        ProductModel newProduct = new ProductModel();
        newProduct.setProductName(productRequest.getProductName());
        newProduct.setProductDescription(productRequest.getProductDescription());
        newProduct.setProductQuantity(productRequest.getProductQuantity());
        newProduct.setProductPrice(productRequest.getProductPrice());
        newProduct.setProductDiscount(productRequest.getProductDiscount());

        Double productOriginalPrice = productRequest.getProductPrice();
        Double discountedPrice = productOriginalPrice - (productRequest.getProductDiscount() * 0.01 * productOriginalPrice);
        newProduct.setProductSpecialPrice(discountedPrice);

        newProduct.setProductImage("example.png");
        newProduct.setCategoryModel(categoryModel);

        ProductModel saved = productRepository.save(newProduct);
        return modelMapper.map(saved, ProductResponse.class);
    }


    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId, Long categoryId) {

        ProductModel productModel = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        CategoryModel categoryModel = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        // Update fields
        productModel.setProductName(productRequest.getProductName());
        productModel.setProductDescription(productRequest.getProductDescription());
        productModel.setProductQuantity(productRequest.getProductQuantity());
        productModel.setProductPrice(productRequest.getProductPrice());
        productModel.setProductDiscount(productRequest.getProductDiscount());

        Double productOriginalPrice = productRequest.getProductPrice();
        Double discountedPrice = productOriginalPrice - (productRequest.getProductDiscount() * 0.01 * productOriginalPrice);
        productModel.setProductSpecialPrice(discountedPrice);

        productModel.setProductImage("example.png");
        productModel.setCategoryModel(categoryModel);

        ProductModel updated = productRepository.save(productModel);
        return modelMapper.map(updated, ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductModel productModel = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        productRepository.delete(productModel);
    }

    @Override
    public PaginatedResponse<ProductResponse> getPaginatedProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber - 1, pageSize, sortByAndOrder);
        Page<ProductModel> paginatedResults = productRepository.findAll(pageDetails);

       List<ProductResponse> list = paginatedResults
               .getContent()
               .stream()
               .map(product -> modelMapper.map(product, ProductResponse.class))
               .toList();

        PaginatedResponse<ProductResponse> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setResponse(list);
        paginatedResponse.setPageNumber(paginatedResults.getNumber() + 1);
        paginatedResponse.setPageSize(paginatedResults.getSize());
        paginatedResponse.setTotalElements(paginatedResults.getTotalElements());
        paginatedResponse.setTotalPages(paginatedResults.getTotalPages());
        paginatedResponse.setLastPage(paginatedResults.isLast());

        return paginatedResponse;
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        CategoryModel categoryModel = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<ProductModel> productsData = productRepository.findByCategoryModel(categoryModel);
        return productsData
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByKeyword(String keyword) {

        List<ProductModel> productsData = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
        return productsData
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }
}
