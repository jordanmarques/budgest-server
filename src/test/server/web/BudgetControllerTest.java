package server.web;


import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import server.AbstractTest;
import server.model.Budget;

@DatabaseSetup(BudgetControllerTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { BudgetControllerTest.DATASET })
public class BudgetControllerTest extends AbstractTest{

    public static final String DATASET = "datasets/BudgetControllerTest.xml";

    @Test
    public void should_get_all_budgets(){
        RestAssured.when()
                .get("/budget")
        .then()
                .log().all()
                .body("$", Matchers.hasSize(2));
    }

    @Test
    public void should_get_budget_by_id(){
        RestAssured.when()
                .get("/budget/1")
        .then()
                .log().all()
                .body("name", Matchers.is("rockinCategory"));
    }

    @Test
    public void should_insert_a_budget(){
        String budgetName = "insertedBudget";

        Budget budget = Budget.builder()
                .name(budgetName)
                .globalAmount(150f)
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(budget)
        .when()
                .post("/budget")
        .then()
                .log().all()
                .body("name", Matchers.is(budgetName));
    }

    @Test
    public void should_delete_a_budget(){
        RestAssured.when()
                .delete("/budget/1")
        .then()
                .log().all()
                .statusCode(200);
    }
}
