package app.controllers;

import app.database.Course;
import app.database.Event;
import app.database.User;
import app.excpetions.NotFoundException;
import app.service.SecurityService;
import app.service.database.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(path = "/login")
    public Integer login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isEmpty()) {
            return 2;
        } else if (user.get().getPassword().equals(password)) {
            return 0;
        } else {
            return 1;
        }
    }

    @PostMapping(path = "/register")
    public Integer register(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        if (userService.getUserByUsername(username).isPresent()) {
            return 1;
        } else {
            User user = new User(username, password, firstName, lastName);
            userService.saveUser(user);
            return 0;
        }
    }

    @PostMapping(path = "/password")
    public Integer password(@RequestParam String username, @RequestParam String currentpassword, @RequestParam String newpassword) {
        User user = securityService.isAuthorizedHttp(username, currentpassword);
        user.setPassword(newpassword);
        return 0;
    }

    @GetMapping(path = "/info")
    public ObjectNode info(@RequestParam String username, @RequestParam String password, @RequestParam("info") String usernameInfo) {
        securityService.isAuthorizedHttp(username, password);
        Optional<User> info = userService.getUserByUsername(usernameInfo);
        if(info.isPresent()) {
            ObjectNode response = objectMapper.createObjectNode();
            response.put("username", info.get().getUsername());
            response.put("firstname", info.get().getFirstName());
            response.put("lastname", info.get().getLastName());
            response.put("role", info.get().getRole().toString());
            return response;
        } else {
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/courses")
    public ArrayList<Integer> courses(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> courses = new ArrayList<>();
        for(Course course : user.getUserCourses()) {
            courses.add(course.getCourseId());
        }
        return courses;
    }

    @GetMapping(path = "/events/involved")
    public ArrayList<Integer> involved(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> involved = new ArrayList<>();
        for(Event event : user.getUserInvolvedEvents()) {
            involved.add(event.getEventId());
        }
        return involved;
    }

    @GetMapping(path = "/events/owns")
    public ArrayList<Integer> owns(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> owns = new ArrayList<>();
        for(Event event : user.getUserOwnsEvents()) {
            owns.add(event.getEventId());
        }
        return owns;
    }
}
