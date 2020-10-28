package app.service.database;

import app.database.Event;
import app.database.EventDatabase;
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

    public void addEvent(Event event) {
        eventDatabase.save(event);
    }
}
