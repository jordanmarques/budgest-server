package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Event;
import server.model.Person;
import server.repository.EventRepository;
import server.repository.PersonRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Event upsertEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        eventRepository.delete(id);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id){
        return eventRepository.getOne(id);
    }

}
