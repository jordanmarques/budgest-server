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
import server.model.Event;

@DatabaseSetup(EventControllerTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { EventControllerTest.DATASET })
public class EventControllerTest extends AbstractTest{

    public static final String DATASET = "datasets/EventControllerTest.xml";

    @Test
    public void should_get_all_events(){
        RestAssured.when()
                .get("/event")
        .then()
                .log().all()
                .body("$", Matchers.hasSize(2));
    }

    @Test
    public void should_get_event_by_id(){
        RestAssured.when()
                .get("/event/1")
        .then()
                .log().all()
                .body("name", Matchers.is("festival"));
    }

    @Test
    public void should_insert_a_budget(){
        String eventName = "insertedEvent";

        Event event = Event.builder()
                .name(eventName)
                .amount(150f)
                .ownerId(1L)
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(event)
        .when()
                .post("/event")
        .then()
                .log().all()
                .body("name", Matchers.is(eventName));
    }

    @Test
    public void should_delete_a_budget(){
        RestAssured.when()
                .delete("/event/1")
        .then()
                .log().all()
                .statusCode(200);
    }
}
