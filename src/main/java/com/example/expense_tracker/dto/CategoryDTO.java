package com.example.expense_tracker.dto;

import com.example.expense_tracker.model.CategoryType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CategoryDTO {
    private Long id;
    private String name;
    private CategoryType type;

    public void formatName(){
        this.name = Arrays.stream(this.name.split("_"))
                .map(word->word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase()).collect(Collectors.joining(" "));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
