package server.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;


@DatabaseSetup(BudgetRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { BudgetRepositoryTest.DATASET })
public class BudgetRepositoryTest extends AbstractRepositoryTest {

    public static final String DATASET = "BudgetRepositoryTest.xml";

    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    public void should_find_all_budgets(){
        assertThat(budgetRepository.findAll(), Matchers.hasSize(2));
    }
}
