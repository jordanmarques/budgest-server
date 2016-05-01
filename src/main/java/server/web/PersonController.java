package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Budget;
import server.model.Person;
import server.repository.PersonRepository;
import server.service.BudgetService;
import server.service.PersonService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> getAll() {
        return personService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Person getById(@PathVariable("id") Long id){
        return  personService.getById(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person) throws Exception {
        return personService.upsertPerson(person);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
    }

    @RequestMapping(path = "/exist/pseudo/{pseudo}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Boolean getByPseudo(@PathVariable("pseudo") String pseudo){
        return personService.findByPseudo(pseudo) != null;
    }

    @RequestMapping(path = "/exist/mail/{mail:.+}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Boolean getByMail(@PathVariable("mail") String mail){
        return personService.findByMail(mail) != null;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Person login(@RequestParam("id") String id,
                         @RequestParam("password") String password){
        Person person = personService.login(id, password);
        if(null == person)
            throw new IllegalArgumentException("Incorrect username or password");
        return person;
    }






}
