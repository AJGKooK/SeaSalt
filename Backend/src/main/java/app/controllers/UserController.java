package app.controllers;

import app.database.entities.Course;
import app.database.entities.Event;
import app.database.entities.Role;
import app.database.entities.User;
import app.excpetions.BadRequestException;
import app.excpetions.NotFoundException;
import app.service.SecurityService;
import app.service.database.CourseService;
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
    CourseService courseService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(path = "/login")
    public Integer login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isEmpty()) {
            return 2;
        } else if (user.get().getPassword().equals(password)) {
            return 0;
        } else {
            return 1;
        }
    }

    @PostMapping(path = "/register")
    public Integer register(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,
                            @RequestParam(required = false) String role, @RequestParam(required = false) String phoneNum, @RequestParam(required = false) String email) {
        if (userService.getUserByUsername(username).isPresent()) {
            return 1;
        } else if (role != null) {
            Role roleEnum = null;
            for (Role e : Role.values()) {
                if (e.toString().equals(role.toUpperCase())) {
                    roleEnum = e;
                    break;
                }
            }
            if (roleEnum != null) {
                User user = new User(username, password, firstName, lastName, roleEnum, phoneNum, email);
                userService.saveUser(user);
                return 0;
            } else {
                throw new BadRequestException();
            }
        } else {
            User user = new User(username, password, firstName, lastName, null, phoneNum, email);
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
        if (info.isPresent()) {
            return getJsonNodes(info.get());
        } else {
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/courses")
    public ArrayList<Integer> courses(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> courses = new ArrayList<>();
        for (Course course : user.getUserCourses()) {
            courses.add(course.getCourseId());
        }
        return courses;
    }

    @GetMapping(path = "/events/involved")
    public ArrayList<Integer> involved(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> involved = new ArrayList<>();
        for (Event event : user.getUserInvolvedEvents()) {
            involved.add(event.getEventId());
        }
        return involved;
    }

    @GetMapping(path = "/events/owns")
    public ArrayList<Integer> owns(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> owns = new ArrayList<>();
        for (Event event : user.getUserOwnsEvents()) {
            owns.add(event.getEventId());
        }
        return owns;
    }

    @GetMapping(path = "/contacts")
    public ArrayList<String> contacts(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<String> contacts = new ArrayList<>();
        for (User contact : user.getUserContacts()) {
            contacts.add(contact.getUsername());
        }
        return contacts;
    }

    @GetMapping(path = "/contactedby")
    public ArrayList<String> contactedBy(@RequestParam String username, @RequestParam String password) {
        User user = securityService.isAuthorizedHttp(username, password);
        ArrayList<String> contacts = new ArrayList<>();
        for (User contact : user.getContactedBy()) {
            contacts.add(contact.getUsername());
        }
        return contacts;
    }

    @PostMapping(path = "/setrole")
    public Role setRole(@RequestParam String username, @RequestParam String password, @RequestParam String role, @RequestParam String usernameToSet) {
        securityService.isAuthorizedAdminHttp(username, password);
        Optional<User> user = userService.getUserByUsername(usernameToSet);
        if (user.isPresent()) {
            Role roleEnum = null;
            for (Role e : Role.values()) {
                if (e.toString().equals(role.toUpperCase())) {
                    roleEnum = e;
                    break;
                }
            }
            if (roleEnum != null) {
                user.get().setRole(roleEnum);
                userService.saveUser(user.get());
                return roleEnum;
            } else {
                throw new BadRequestException();
            }
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/edit")
    public ObjectNode editUser(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String userToEdit,
                               @RequestParam(required = false) String phonenum, @RequestParam(required = false) String email) {
        if (userToEdit != null) {
            securityService.isAuthorizedAdminHttp(username, password);
            Optional<User> user = userService.getUserByUsername(userToEdit);
            if (user.isPresent()) {
                if (phonenum != null) {
                    user.get().setPhoneNum(phonenum);
                }
                if (email != null) {
                    user.get().setEmail(email);
                }
                userService.saveUser(user.get());
                ObjectNode response = objectMapper.createObjectNode();
                response.put("username", user.get().getUsername());
                response.put("phonenum", user.get().getPhoneNum());
                response.put("email", user.get().getEmail());
                return response;
            } else {
                throw new NotFoundException();
            }
        } else {
            User user = securityService.isAuthorizedHttp(username, password);
            if (phonenum != null) {
                user.setPhoneNum(phonenum);
            }
            if (email != null) {
                user.setEmail(email);
            }
            userService.saveUser(user);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("username", user.getUsername());
            response.put("phonenum", user.getPhoneNum());
            response.put("email", user.getEmail());
            return response;
        }
    }

    @PostMapping(path = "/addcourse")
    public Integer addCourse(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        User user = securityService.isAuthorizedHttp(username, password);
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            user.addCourse(course.get());
            userService.saveUser(user);
            return course.get().getCourseId();
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/delcourse")
    public Integer delCourse(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        User user = securityService.isAuthorizedHttp(username, password);
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            user.delCourse(course.get());
            userService.saveUser(user);
            return course.get().getCourseId();
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/addcontact")
    public ObjectNode addContact(@RequestParam String username, @RequestParam String password, @RequestParam(name = "contact") String contactUsername) {
        User user = securityService.isAuthorizedHttp(username, password);
        Optional<User> contact = userService.getUserByUsername(contactUsername);
        if (contact.isPresent()) {
            user.addContact(contact.get());
            userService.saveUser(user);
            return getJsonNodes(contact.get());
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/delcontact")
    public String delContact(@RequestParam String username, @RequestParam String password, @RequestParam(name = "contact") String contactUsername) {
        User user = securityService.isAuthorizedHttp(username, password);
        Optional<User> contact = userService.getUserByUsername(contactUsername);
        if (contact.isPresent()) {
            user.delContact(contact.get());
            userService.saveUser(user);
            return contact.get().getUsername();
        } else {
            throw new NotFoundException();
        }
    }

    private ObjectNode getJsonNodes(User user) {
        ObjectNode response = objectMapper.createObjectNode();
        response.put("username", user.getUsername());
        response.put("firstname", user.getFirstName());
        response.put("lastname", user.getLastName());
        if (user.getRole() == null) {
            response.put("role", "null");
        } else {
            response.put("role", user.getRole().toString());
        }
        if (user.getPhoneNum() == null) {
            response.put("phonenum", "null");
        } else {
            response.put("phoneNum", user.getPhoneNum());
        }
        if (user.getEmail() == null) {
            response.put("email", "null");
        } else {
            response.put("email", user.getEmail());
        }

        return response;
    }
}
