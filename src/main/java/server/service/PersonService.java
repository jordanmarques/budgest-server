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

    public Person upsertPerson(Person budget) {
        return personRepository.save(budget);
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

}
