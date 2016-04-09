package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Budget;
import server.repository.BudgetRepository;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    public Budget upsertBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id){
        budgetRepository.delete(id);
    }

    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }

    public Budget getById(Long id){
        return budgetRepository.getOne(id);
    }
}
