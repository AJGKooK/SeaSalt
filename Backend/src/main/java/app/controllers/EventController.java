package app.controllers;

import app.database.entities.Assignment;
import app.database.entities.Course;
import app.database.entities.Event;
import app.database.entities.User;
import app.excpetions.NotFoundException;
import app.service.SecurityService;
import app.service.database.AssignmentService;
import app.service.database.CourseService;
import app.service.database.EventService;
import app.service.database.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    SecurityService securityService;

    @Autowired
    CourseService courseService;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    AssignmentService assignmentService;

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
        if (event.isPresent()) {
            if (event.get().getEventCourse() == null) {
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
        if (event.isPresent()) {
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

    @PostMapping(path = "/add")
    public Integer add(@RequestParam String username, @RequestParam String password, @RequestParam String eventName, @RequestParam String eventDesc,
                       @RequestParam Integer eventTime, @RequestParam boolean owner, @RequestParam(required = false) Integer courseId) {
        if (courseId != null) {
            Optional<Course> course = courseService.getCourseById(courseId);
            if (owner) {
                if (course.isPresent()) {
                    User user = securityService.isAuthorizedHttp(username, password, course.get());
                    Event event = new Event(eventName, eventDesc, eventTime, user, course.get());
                    event.addUser(user);
                    eventService.saveEvent(event);
                    return event.getEventId();
                } else {
                    securityService.isAuthorizedHttp(username, password);
                    throw new NotFoundException();
                }
            } else {
                if (course.isPresent()) {
                    User user = securityService.isAuthorizedHttp(username, password, course.get());
                    Event event = new Event(eventName, eventDesc, eventTime, course.get());
                    event.addUser(user);
                    eventService.saveEvent(event);
                    return event.getEventId();
                } else {
                    securityService.isAuthorizedHttp(username, password);
                    throw new NotFoundException();
                }
            }
        } else {
            if (owner) {
                User user = securityService.isAuthorizedHttp(username, password);
                Event event = new Event(eventName, eventDesc, eventTime, user);
                event.addUser(user);
                eventService.saveEvent(event);
                return event.getEventId();
            } else {
                User user = securityService.isAuthorizedHttp(username, password);
                Event event = new Event(eventName, eventDesc, eventTime);
                event.addUser(user);
                eventService.saveEvent(event);
                return event.getEventId();
            }
        }
    }

    @PostMapping(path = "/edit")
    public Integer edit(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam(required = false) String eventName,
                        @RequestParam(required = false) String eventDesc, @RequestParam(required = false) Integer eventTime, @RequestParam(required = false) Integer courseId) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            if (courseId != null) {
                Optional<Course> course = courseService.getCourseById(courseId);
                if (course.isPresent()) {
                    securityService.isAuthorizedOwnerHttp(username, password, event.get(), course.get());
                    if (eventName != null) {
                        event.get().setEventName(eventName);
                    }
                    if (eventDesc != null) {
                        event.get().setEventDesc(eventDesc);
                    }
                    if (eventTime != null) {
                        event.get().setEventTime(eventTime);
                    }
                    event.get().setEventCourse(course.get());
                    eventService.saveEvent(event.get());
                    return event.get().getEventId();
                } else {
                    securityService.isAuthorizedHttp(username, password);
                    throw new NotFoundException();
                }
            } else {
                securityService.isAuthorizedOwnerHttp(username, password, event.get());
                if (eventName != null) {
                    event.get().setEventName(eventName);
                }
                if (eventDesc != null) {
                    event.get().setEventDesc(eventDesc);
                }
                if (eventTime != null) {
                    event.get().setEventTime(eventTime);
                }
                eventService.saveEvent(event.get());
                return event.get().getEventId();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/adduser")
    public Integer addUser(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam String usernameToAdd) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            securityService.isAuthorizedHttp(username, password, event.get());
            Optional<User> user = userService.getUserByUsername(usernameToAdd);
            if (user.isPresent()) {
                event.get().addUser(user.get());
                eventService.saveEvent(event.get());
                return event.get().getEventId();
            } else {
                securityService.isAuthorizedHttp(username, password);
                throw new NotFoundException();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/deluser")
    public Integer delUser(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam String usernameToDel) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            securityService.isAuthorizedOwnerHttp(username, password, event.get());
            Optional<User> user = userService.getUserByUsername(usernameToDel);
            if (user.isPresent()) {
                event.get().delUser(user.get());
                eventService.saveEvent(event.get());
                return event.get().getEventId();
            } else {
                securityService.isAuthorizedHttp(username, password);
                throw new NotFoundException();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/addassignment")
    public Integer addAssignment(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam Integer assignmentId) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            securityService.isAuthorizedOwnerHttp(username, password, event.get());
            Optional<Assignment> assignment = assignmentService.getAssignmentById(assignmentId);
            if (assignment.isPresent()) {
                event.get().addAssignment(assignment.get());
                eventService.saveEvent(event.get());
                return event.get().getEventId();
            } else {
                securityService.isAuthorizedHttp(username, password);
                throw new NotFoundException();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/delassignment")
    public Integer delAssignment(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam Integer assignmentId) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            securityService.isAuthorizedOwnerHttp(username, password, event.get());
            Optional<Assignment> assignment = assignmentService.getAssignmentById(assignmentId);
            if (assignment.isPresent()) {
                event.get().delAssignment(assignment.get());
                eventService.saveEvent(event.get());
                return event.get().getEventId();
            } else {
                securityService.isAuthorizedHttp(username, password);
                throw new NotFoundException();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/delevent")
    public boolean delEvent(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            Course course = event.get().getEventCourse();
            if (course != null) {
                securityService.isAuthorizedOwnerHttp(username, password, event.get(), course);
            } else {
                securityService.isAuthorizedOwnerHttp(username, password, event.get());
            }
            try {
                eventService.deleteEvent(event.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    private ObjectNode getJsonNodes(Event event) {
        ObjectNode response = objectMapper.createObjectNode();
        response.put("id", "\"" + event.getEventId() + "\"");
        response.put("name", event.getEventName());
        response.put("desc", event.getEventDesc());
        response.put("time", "\"" + event.getEventTime() + "\"");
        if (event.getEventOwner() != null) {
            response.put("owner", event.getEventOwner().getUsername());
        }
        if (event.getEventCourse() != null) {
            response.put("course", "\"" + event.getEventCourse().getCourseId() + "\"");
        }
        return response;
    }
}
