package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test/expenses")
public class TestController {

    @Autowired
    ExpenseService expenseService;
    @GetMapping
    public List<Expense> testEndpoint() {
        return expenseService.getAllExpenses();
    }
}
