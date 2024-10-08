package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.expense_tracker.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id){
        return expenseRepository.findById(id);
    }

    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expense){
        if(expenseRepository.existsById(id)){
            expense.setId(id);
            return expenseRepository.save(expense);
        }
        return null;
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }
}
