package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.CategoryDTO;
import com.example.expense_tracker.mapper.CategoryMapper;
import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.model.CategoryType;
import com.example.expense_tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOS = CategoryMapper.toDTOList(categories);
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<CategoryDTO>> getAllCategoryByType(@PathVariable CategoryType type){
        List<Category> categories = categoryService.getCategoriesByType(type);
        if(categories.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<CategoryDTO> categoryDTOs = CategoryMapper.toDTOList(categories);
        return ResponseEntity.ok(categoryDTOs);

    }


}
