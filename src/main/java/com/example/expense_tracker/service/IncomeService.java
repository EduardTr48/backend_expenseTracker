package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Income;
import com.example.expense_tracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncomes(){
        return incomeRepository.findAll();
    }

    public Optional<Income> getIncomeById(Long id){
        return incomeRepository.findById(id);
    }

    public Income addIncome(Income income){
        return incomeRepository.save(income);
    }

    public Income updateIncome(Long id, Income income){
        if(incomeRepository.existsById(id)){
            income.setId(id);
            return incomeRepository.save(income);
        }
        return null;
    }

    public void deleteIncome(Long id){
        incomeRepository.deleteById(id);
    }
}
