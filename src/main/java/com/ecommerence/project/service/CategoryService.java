package com.ecommerence.project.service;

import com.ecommerence.project.model.Category;

import java.util.List;


public interface CategoryService {
    public List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
