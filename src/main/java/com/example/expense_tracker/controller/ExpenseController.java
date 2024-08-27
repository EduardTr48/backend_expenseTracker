package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.ExpenseDTO;
import com.example.expense_tracker.mapper.ExpenseMapper;
import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.expense_tracker.service.ExpenseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5173")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(){
        List<Expense> expenses = expenseService.getAllExpenses();
        List<ExpenseDTO> expenseDTOS = ExpenseMapper.toDTOList(expenses);
        return ResponseEntity.ok(expenseDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id){
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);
        if(optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();
            ExpenseDTO expenseDTO = ExpenseMapper.toDTO(expense);
            return ResponseEntity.ok(expenseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }


    }
    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO){
        Optional<Category> optionalCategory = categoryService.getCategoryById(expenseDTO.getCategoryId());
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            Expense newExpense = ExpenseMapper.toEntity(expenseDTO, category);
            expenseService.addExpense(newExpense);
            ExpenseDTO responseDTO = ExpenseMapper.toDTO(newExpense);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO){
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);
        if(optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();
            expense.setName(expenseDTO.getName());
            expense.setAmount(expenseDTO.getAmount());
            expense.setDate(expenseDTO.getDate());

            Optional<Category> optionalCategory = categoryService.getCategoryById(expenseDTO.getCategoryId());

            if(optionalCategory.isPresent()){
                expense.setCategory(optionalCategory.get());
            }

            expenseService.updateExpense(id, expense);
            return ResponseEntity.ok(expense);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long id){
        if(expenseService.getExpenseById(id).isPresent()){
            expenseService.deleteExpense(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
