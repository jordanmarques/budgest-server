package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Budget;
import server.repository.BudgetRepository;

import java.util.List;

/**
 * Created by Yohan on 03/04/2016.
 */

@Service
public class BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    public Budget addBudget(Budget budget)
    {
        return budgetRepository.save(budget);
    }

    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }
}
