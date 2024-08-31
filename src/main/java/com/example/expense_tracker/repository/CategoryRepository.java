package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByType(CategoryType type);
}
