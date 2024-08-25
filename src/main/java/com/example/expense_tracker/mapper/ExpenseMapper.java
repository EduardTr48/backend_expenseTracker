package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.ExpenseDTO;
import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseMapper {
    public static ExpenseDTO toDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setName(expense.getName());
        dto.setCategoryId(expense.getCategory().getId());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        return dto;
    }

    public static Expense toEntity(ExpenseDTO dto, Category category) {
        Expense expense = new Expense();
        expense.setId(dto.getId());
        expense.setName(dto.getName());
        expense.setCategory(category);
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        return expense;
    }

    public static List<ExpenseDTO> toDoList(List<Expense> expenses){
        List<ExpenseDTO> expenseDTOs = new ArrayList<>();
        for(Expense expense: expenses){
            ExpenseDTO dto = toDTO(expense);
            expenseDTOs.add(dto);
        }
        return expenseDTOs;
    }

}
