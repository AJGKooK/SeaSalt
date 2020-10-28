package app.controllers;

import app.database.Assignment;
import app.database.Event;
import app.database.User;
import app.excpetions.NotFoundException;
import app.service.EventService;
import app.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    SecurityService securityService;

    @Autowired
    EventService eventService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "/discover")
    public ArrayList<Integer> discover(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> events = new ArrayList<>();
        for (Event event : eventService.getAllEvents()) {
            if (event.getEventCourse() == null) {
                events.add(event.getEventId());
            } else if (event.getEventCourse().getCourseUsers().contains(user)) {
                events.add(event.getEventId());
            }
        }
        return events;
    }

    @GetMapping(path = "/info")
    public ObjectNode info(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Event> event = eventService.getEventById(id);
        if(event.isPresent()) {
            if(event.get().getEventCourse() == null) {
                securityService.isAuthorizedHttp(username, password);
                return getJsonNodes(event.get());
            } else {
                securityService.isAuthorizedHttp(username, password, event.get().getEventCourse());
                return getJsonNodes(event.get());
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/users")
    public ArrayList<String> users(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Event> event = eventService.getEventById(id);
        if(event.isPresent()) {
            securityService.isAuthorizedHttp(username, password, event.get());
            ArrayList<String> users = new ArrayList<>();
            for (User user : event.get().getEventUsers()) {
                users.add(user.getUsername());
            }
            return users;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/assignment")
    public ArrayList<Integer> assignments(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            securityService.isAuthorizedHttp(username, password, event.get());
            ArrayList<Integer> assignments = new ArrayList<>();
            for (Assignment assignment : event.get().getEventAssignments()) {
                assignments.add(assignment.getAssignmentId());
            }
            return assignments;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    private ObjectNode getJsonNodes(Event event) {
        ObjectNode response = objectMapper.createObjectNode();
        response.put("id", event.getEventId());
        response.put("name", event.getEventName());
        response.put("desc", event.getEventDesc());
        response.put("time", event.getEventDesc());
        if(event.getEventOwner() != null) {
            response.put("owner", event.getEventOwner().getUsername());
        }
        return response;
    }
}