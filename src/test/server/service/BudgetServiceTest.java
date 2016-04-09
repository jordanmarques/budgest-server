package server.service;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import server.AbstractTest;
import server.model.Budget;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;


@DatabaseSetup(BudgetServiceTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { BudgetServiceTest.DATASET })
public class BudgetServiceTest extends AbstractTest {

    public static final String DATASET = "datasets/BudgetServiceTest.xml";

    @Autowired
    private BudgetService budgetService;

    @Test
    public void should_find_all_budgets(){
        assertThat(budgetService.getAll(), Matchers.hasSize(2));
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_budget_whitout_name_is_upserted(){
        Budget budget = Budget.builder()
                .globalAmount(1f)
                .build();
        budgetService.upsertBudget(budget);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_budget_whith_empty_name_is_upserted(){
        Budget budget = Budget.builder()
                .globalAmount(1f)
                .name("")
                .build();
        budgetService.upsertBudget(budget);
    }

    @Test
    public void should_delete_a_budget(){
        budgetService.deleteBudget(1L);
        assertThat(budgetService.getAll(), Matchers.hasSize(1));
    }

}
