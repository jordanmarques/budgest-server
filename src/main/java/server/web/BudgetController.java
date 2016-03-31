package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.model.Budget;
import server.repository.BudgetRepository;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    BudgetRepository budgetRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }
}
