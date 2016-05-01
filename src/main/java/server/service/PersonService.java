package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Person;
import server.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private BudgetService budgetService;

    @Autowired
    public PersonService(BudgetService budgetService, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.budgetService = budgetService;
    }

    public Person upsertPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id){
        Person person = getById(id);
        if(null != person.getBudgets()){
            person.getBudgets().stream()
                    .forEach(budget -> budgetService.deleteBudget(budget.getBudgetId()));
        }

        personRepository.delete(id);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id){
        return personRepository.findOne(id);
    }

    public Person login(String pseudo, String password){
        List<Person> persons = personRepository.login(pseudo, password);
        return (persons.size() == 0) ? null : persons.get(0);
    }

    public Person findByPseudo(String pseudo){
        List<Person> persons = personRepository.findByPseudo(pseudo);
        return (persons.size() == 0) ? null : persons.get(0);
    }

    public Person findByMail(String mail){
        List<Person> persons = personRepository.findByMailAdress(mail);
        return (persons.size() == 0) ? null : persons.get(0);
    }
}
