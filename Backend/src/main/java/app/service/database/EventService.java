package app.service.database;

import app.database.EventDatabase;
import app.database.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventDatabase eventDatabase;

    public List<Event> getAllEvents() {
        return eventDatabase.findAll();
    }

    public Optional<Event> getEventById(Integer id) {
        return eventDatabase.findById(id);
    }

    public void saveEvent(Event event) {
        eventDatabase.save(event);
    }

    public void deleteEvent(Event event) {
        eventDatabase.delete(event);
    }

    public void deleteEvent(Integer id) {
        eventDatabase.deleteById(id);
    }
}
