package server.service;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import server.AbstractTest;
import server.model.Budget;
import server.model.Person;

import javax.validation.ConstraintViolationException;

import java.util.Date;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;

@DatabaseSetup(PersonServiceTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { PersonServiceTest.DATASET })
public class PersonServiceTest extends AbstractTest {

    public static final String DATASET = "datasets/PersonServiceTest.xml";

    @Autowired
    private PersonService personService;

    @Test
    public void should_find_all_persons(){
        assertThat(personService.getAll(), Matchers.hasSize(2));
    }

    @Test
    public void should_delete_a_person(){
        personService.deletePerson(1L);
        assertThat(personService.getAll(), Matchers.hasSize(1));
    }

    @Test
    public void should_add_a_person(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("Tutu")
                .pseudo("Toutou")
                .password("Rourou")
                .birthdate(new Date(116, 4, 9))
                .phoneNumber("0600000000")
                .mail("totor@dudule.com")
                .budgets(new HashSet<Budget>())
                .build();
        personService.upsertPerson(person);
        assertThat(personService.getAll(), Matchers.hasSize(3));
    }

    // firstName
    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whitout_a_first_name_is_upserted(){
        Person person = Person.builder()
                .lastName("Tutu")
                .pseudo("Toutou")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("totor@dudule.com")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whith_an_empty_first_name_is_upserted(){
        Person person = Person.builder()
                .firstName("")
                .lastName("Tutu")
                .pseudo("Toutou")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("totor@dudule.com")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    // lastName
    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whitout_a_last_name_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .pseudo("Toutou")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("totor@dudule.com")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whith_an_empty_last_name_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("")
                .pseudo("Toutou")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("totor@dudule.com")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    // pseudo
    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whitout_a_pseudo_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("Tutu")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("totor@dudule.com")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whith_an_empty_pseudo_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("Tutu")
                .pseudo("")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("totor@dudule.com")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    // mail
    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whitout_a_mail_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("Tutu")
                .pseudo("Toutou")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whith_an_empty_mail_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("Tutu")
                .pseudo("Toutou")
                .password("Rourou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }

    // password
    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_a_person_whithout_a_password_is_upserted(){
        Person person = Person.builder()
                .firstName("Toto")
                .lastName("Tutu")
                .pseudo("Toutou")
                //.birthdate(new Date())
                //.phoneNumber("0600000000")
                .mail("")
                //.budgets(new Set<Budget>())
                .build();
        personService.upsertPerson(person);
    }
}