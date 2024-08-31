package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.IncomeDTO;
import com.example.expense_tracker.mapper.IncomeMapper;
import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.model.Income;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incomes")
@CrossOrigin(origins = "http://localhost:5173")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getAllIncomes(){
        List<Income> incomes = incomeService.getAllIncomes();
        List<IncomeDTO> incomeDTOs = IncomeMapper.toDTOList(incomes);
        return ResponseEntity.ok(incomeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable Long id){
        Optional<Income> optionalIncome = incomeService.getIncomeById(id);
        if(optionalIncome.isPresent()){
            Income income = optionalIncome.get();
            IncomeDTO incomeDTO = IncomeMapper.toDTO(income);
            return ResponseEntity.ok(incomeDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<IncomeDTO> addIncome(@RequestBody IncomeDTO incomeDTO){
        Optional<Category> optionalCategory = categoryService.getCategoryById(incomeDTO.getCategoryId());
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            Income newIncome = IncomeMapper.toEntity(incomeDTO, category);
            Income response = incomeService.addIncome(newIncome);
            IncomeDTO responseDTO = IncomeMapper.toDTO(response);
            return ResponseEntity.ok(responseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeService.getIncomeById(id);
        if(optionalIncome.isPresent()){
            Income income = optionalIncome.get();;
            income.setName(incomeDTO.getName());
            income.setDate(incomeDTO.getDate());
            income.setAmount(incomeDTO.getAmount());

            Optional<Category> optionalCategory = categoryService.getCategoryById(incomeDTO.getCategoryId());
            if(optionalCategory.isPresent()){
                income.setCategory(optionalCategory.get());
            }
            Income response = incomeService.updateIncome(id,income);
            IncomeDTO responseDTO = IncomeMapper.toDTO(response);
            return ResponseEntity.ok(responseDTO);

        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIncome(@PathVariable Long id){
        if(incomeService.getIncomeById(id).isPresent()){
            incomeService.deleteIncome(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
