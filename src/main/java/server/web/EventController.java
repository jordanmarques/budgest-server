package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Budget;
import server.model.Event;
import server.model.Person;
import server.service.BudgetService;
import server.service.EventService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAll() {
        return eventService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Event getById(@PathVariable("id") Long id){
        return  eventService.getById(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Event addBudget(@RequestBody Event event) throws Exception {
        return eventService.upsertEvent(event);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBudget(@PathVariable("id") Long id){
        eventService.deleteEvent(id);
    }

}
