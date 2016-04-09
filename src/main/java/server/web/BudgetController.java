package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Budget;
import server.service.BudgetService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Budget> getAll() {
        return budgetService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Budget getById(@PathVariable("id") Long id){
        return  budgetService.getById(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Budget addBudget(@RequestBody Budget budget) throws Exception {
        return budgetService.upsertBudget(budget);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBudget(@PathVariable("id") Long id){
        budgetService.deleteBudget(id);
    }

}
