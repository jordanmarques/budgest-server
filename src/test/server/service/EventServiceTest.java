package server.service;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import server.AbstractTest;
import server.model.Event;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;


@DatabaseSetup(EventServiceTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { EventServiceTest.DATASET })
public class EventServiceTest extends AbstractTest {

    public static final String DATASET = "datasets/EventServiceTest.xml";

    @Autowired
    private EventService eventService;

    @Autowired
    private PersonService personService;

    @Test
    public void should_find_all_events(){
        assertThat(eventService.getAll(), Matchers.hasSize(2));
    }

    @Test
    public void should_find_event(){
        Event byId = eventService.getById(1L);
        assertThat(byId.getEventId(), Matchers.is(1L));
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_an_event_whitout_name_is_upserted(){
        Event event = Event.builder()
                .amount(1f)
                .ownerId(1L)
                .build();
        eventService.upsertEvent(event);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_an_event_whith_empty_name_is_upserted(){
        Event event = Event.builder()
                .amount(1f)
                .ownerId(1L)
                .name("")
                .build();
        eventService.upsertEvent(event);
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_an_exception_when_an_event_whith_empty_ownerId_is_upserted() {
        Event event = Event.builder()
                .amount(1f)
                .name("toto")
                .build();
        eventService.upsertEvent(event);
    }

    @Test
    public void should_delete_an_event_without_delete_associated_persons(){
        eventService.deleteEvent(1L);
        assertThat(eventService.getAll(), Matchers.hasSize(1));
        assertThat(personService.getAll(), Matchers.hasSize(2));
    }

}
