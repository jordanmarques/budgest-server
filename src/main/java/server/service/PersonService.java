package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Person;
import server.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person upsertPerson(Person budget) {
        return personRepository.save(budget);
    }

    public void deletePerson(Long id){
        personRepository.delete(id);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id){
        return personRepository.findOne(id);
    }

}
