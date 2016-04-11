package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Event;
import server.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    private PersonService personService;

    @Autowired
    public EventService(EventRepository eventRepository, PersonService personService) {
        this.eventRepository = eventRepository;
        this.personService = personService;
    }

    public Event upsertEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        Event event = getById(id);
        event.getPersons().stream().forEach(person -> {
            person.getEvents().remove(event);
            personService.upsertPerson(person);
        });

        eventRepository.delete(id);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id){
        return eventRepository.findOne(id);
    }

}
