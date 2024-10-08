package com.ecommerence.project.controller;
import com.ecommerence.project.config.AppConstants;
import com.ecommerence.project.model.Category;
import com.ecommerence.project.payload.CategoryDTO;
import com.ecommerence.project.payload.CategoryResponse;
import com.ecommerence.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
                                                             @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE) Integer pageSize){
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @RequestMapping(value="/public/categories", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value="/admin/categories/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
        CategoryDTO categoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
    @RequestMapping(value="/public/categories/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long categoryId){

        CategoryDTO savedCategory = categoryService.updateCategory(categoryDTO, categoryId );
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }
}
