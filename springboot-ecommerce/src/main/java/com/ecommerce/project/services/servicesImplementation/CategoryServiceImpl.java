package com.ecommerce.project.services.servicesImplementation;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.models.CategoryModel;
import com.ecommerce.project.payloads.RequestDTOs.CategoryRequest;
import com.ecommerce.project.payloads.ResponseDTOs.CategoryResponse;
import com.ecommerce.project.payloads.common.PaginatedResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {

        List<CategoryModel> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new APIException("No category is present");
        }

        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .toList();
    }

    @Override
    public CategoryResponse getSingleCategory(Long categoryId) {

        CategoryModel category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse createNewCategory(CategoryRequest category) {
        CategoryModel existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (existingCategory != null) {
            throw new APIException("Category already exists: " + category.getCategoryName());
        }

        CategoryModel categoryEntity = modelMapper.map(category, CategoryModel.class);
        CategoryModel savedData = categoryRepository.save(categoryEntity);
        return modelMapper.map(savedData, CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        CategoryModel existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        existingCategory.setCategoryName(categoryRequest.getCategoryName());
        CategoryModel updatedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryModel category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public PaginatedResponse<CategoryResponse> getPaginatedResults(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber - 1, pageSize, sortByAndOrder);
        Page<CategoryModel> paginatedResults = categoryRepository.findAll(pageDetails);
        List<CategoryResponse> list = paginatedResults
                .getContent()
                .stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .toList();

        PaginatedResponse<CategoryResponse> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setResponse(list);
        paginatedResponse.setPageNumber(paginatedResults.getNumber() + 1);
        paginatedResponse.setPageSize(paginatedResults.getSize());
        paginatedResponse.setTotalElements(paginatedResults.getTotalElements());
        paginatedResponse.setTotalPages(paginatedResults.getTotalPages());
        paginatedResponse.setLastPage(paginatedResults.isLast());

        return paginatedResponse;
    }
}
