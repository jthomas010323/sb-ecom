package com.ecommerence.project.service;

import com.ecommerence.project.exceptions.APIException;
import com.ecommerence.project.exceptions.ResourceNotFoundException;
import com.ecommerence.project.model.Category;
import com.ecommerence.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories =categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No categories");
        return categories;
    }
    @Override
    public void createCategory(Category category) {
        Category savedCategory= categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!= null){
            throw new APIException("Catagory with the name " + category.getCategoryName() + " already exists");
        }
        categoryRepository.save(category);
    }
    @Override
    public String deleteCategory(Long categoryId){
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return "Deleted ID: " + categoryId;
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId).stream().findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            categoryRepository.save(existingCategory);
            return existingCategory;
        }
        else{
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }
    }
}
