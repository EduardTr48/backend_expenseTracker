package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.CategoryDTO;
import com.example.expense_tracker.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryDTO toDTO (Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setType(category.getType());

        return categoryDTO;
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category: categories){
            CategoryDTO categoryDTO = toDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }
}
