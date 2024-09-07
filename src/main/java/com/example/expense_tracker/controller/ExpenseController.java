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
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);
        if(optionalExpense.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El gasto no fue encontrado");
        }
        Expense expense = optionalExpense.get();
        ExpenseDTO expenseDTO = ExpenseMapper.toDTO(expense);
        return ResponseEntity.ok(expenseDTO);
    }
    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody ExpenseDTO expenseDTO){
        Optional<Category> optionalCategory = categoryService.getCategoryById(expenseDTO.getCategoryId());
        if(optionalCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria no existe");
        }

        Category category = optionalCategory.get();
        Expense newExpense = ExpenseMapper.toEntity(expenseDTO, category);
        expenseService.addExpense(newExpense);
        ExpenseDTO responseDTO = ExpenseMapper.toDTO(newExpense);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO){
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);

        if(optionalExpense.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El gasto no fue encontrado");

        }

        Expense expense = optionalExpense.get();
        expense.setName(expenseDTO.getName());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());

        Optional<Category> optionalCategory = categoryService.getCategoryById(expenseDTO.getCategoryId());

        if(optionalCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria no existe");

        }
        expense.setCategory(optionalCategory.get());

        expenseService.updateExpense(id, expense);
        ExpenseDTO responseDTO = ExpenseMapper.toDTO(expense);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);
        if(optionalExpense.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El gasto no existe");
        }
        expenseService.deleteExpense(id);

        return ResponseEntity.status(HttpStatus.OK).body("El gasto fue eliminado existosamente");

    }


}
