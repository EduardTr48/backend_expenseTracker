package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.IncomeDTO;
import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.model.Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeMapper {
    public static IncomeDTO toDTO(Income income){
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(income.getId());
        incomeDTO.setAmount(income.getAmount());
        incomeDTO.setName(income.getName());
        incomeDTO.setCategoryId(income.getCategory().getId());
        incomeDTO.setDate(income.getDate());

        return incomeDTO;

    }

    public static Income toEntity(IncomeDTO incomeDTO, Category category){
        Income income = new Income();
        income.setId(incomeDTO.getId());
        income.setName(incomeDTO.getName());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(category);
        income.setDate(incomeDTO.getDate());

        return income;
    }

    public static List<IncomeDTO> toDTOList(List<Income> incomes){
        List<IncomeDTO> incomeDTOs = new ArrayList<>();
        for (Income income: incomes){
            IncomeDTO incomeDTO = toDTO(income);
            incomeDTOs.add(incomeDTO);
        }
        return incomeDTOs;
    }
}
