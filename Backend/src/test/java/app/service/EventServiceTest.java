package app.service;

import app.database.entities.Event;
import app.service.database.EventService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

public class EventServiceTest {

    EventService service;

    @Mock
    Event event;

    @Test
    public void canFindEvent()
    {
        service.saveEvent(event);

        Optional<Event> optionalEvent = service.getEventById(event.getEventId());

        Assert.assertTrue(optionalEvent.isPresent());
        Assert.assertEquals(optionalEvent.get(), event);
    }
}