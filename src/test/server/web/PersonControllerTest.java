package server.web;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import server.AbstractTest;
import server.AbstractTestIntegration;
import server.ApplicationIntegration;
import server.model.Budget;
import server.model.Person;
import server.service.PersonService;

import java.util.Date;
import java.util.HashSet;


@DatabaseSetup(PersonControllerTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { PersonControllerTest.DATASET })
public class PersonControllerTest extends AbstractTestIntegration {

    @Autowired
    private PersonService personService;

//    @Before
//    public void insertPersons(){
//        Person person = Person.builder()
//                .personId(1L)
//                .birthdate(new Date(116, 4, 9))
//                .firstName("Stan")
//                .lastName("Laurel")
//                .mail("titi@machinbidule.com")
//                .password("dudule")
//                .phoneNumber("0632145896")
//                .pseudo("Laurel").build();
//
//        Person person2 = Person.builder()
//                .personId(2L)
//                .birthdate(new Date(116, 4, 9))
//                .firstName("Olivier")
//                .lastName("Hardy")
//                .mail("tutu@machinbidule.com")
//                .password("totor")
//                .phoneNumber("0632145896")
//                .pseudo("Hardy").build();
//
//        personService.upsertPerson(person);
//        personService.upsertPerson(person2);
//    }
//
//    @After
//    public void deletePersons(){
//        personService.deletePerson(1L);
//        personService.deletePerson(2L);
//    }

    public static final String DATASET = "datasets/PersonControllerTest.xml";

    @Test
    public void should_get_all_persons(){
        RestAssured.when()
                .get("/person")
                .then()
                .log().all()
                .body("$", Matchers.hasSize(2));
    }

    @Test
    public void should_get_person_by_id(){
        RestAssured.when()
                .get("/person/1")
                .then()
                .log().all()
                .body("firstName", Matchers.is("Stan"));
    }

    @Test
    public void should_insert_a_person(){
        String firstName = "Roro";

        Person person = Person.builder()
                .firstName(firstName)
                .lastName("Lulu")
                .pseudo("Poupou")
                .password("azerty")
                .birthdate(new Date(116, 4, 9))
                .phoneNumber("0600000000")
                .mail("aze@rty.com")
                .budgets(new HashSet<Budget>())
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(person)
            .when()
                .post("/person")
            .then()
                .log().all()
                .body("firstName", Matchers.is(firstName));
    }

    @Test
    public void should_delete_a_person(){
        RestAssured.when()
                .delete("/person/1")
                .then()
                .log().all()
                .statusCode(200);
    }
}
