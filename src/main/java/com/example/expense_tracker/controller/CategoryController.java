package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.CategoryDTO;
import com.example.expense_tracker.mapper.CategoryMapper;
import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
