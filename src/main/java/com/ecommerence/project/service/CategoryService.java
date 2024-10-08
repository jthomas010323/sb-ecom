package com.ecommerence.project.service;

import com.ecommerence.project.model.Category;
import com.ecommerence.project.payload.CategoryDTO;
import com.ecommerence.project.payload.CategoryResponse;

import java.util.List;


public interface CategoryService {
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
