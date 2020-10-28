package app.controllers;

import app.database.Assignment;
import app.database.Course;
import app.database.User;
import app.excpetions.NotFoundException;
import app.service.database.CourseService;
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
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    SecurityService securityService;

    @Autowired
    CourseService courseService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "/discover")
    public ArrayList<Integer> discover(@RequestParam String username, @RequestParam String password) {
        securityService.isAuthorizedHttp(username, password);
        ArrayList<Integer> courses = new ArrayList<>();
        for (Course course : courseService.getAllCourses()) {
            courses.add(course.getCourseId());
        }
        return courses;
    }

    @GetMapping(path = "/info")
    public ObjectNode info(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        securityService.isAuthorizedHttp(username, password);
        Optional<Course> course = courseService.getCourseById(id);
        if(course.isPresent()) {
            ObjectNode response = objectMapper.createObjectNode();
            response.put("id", course.get().getCourseId());
            response.put("name", course.get().getCourseName());
            response.put("desc", course.get().getCourseDesc());
            response.put("time", course.get().getCourseTime());
            return response;
        } else {
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/users")
    public ArrayList<String> users(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        if(course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            ArrayList<String> users = new ArrayList<>();
            for(User user : course.get().getCourseUsers()) {
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
        Optional<Course> course = courseService.getCourseById(id);
        if(course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            ArrayList<Integer> assignments = new ArrayList<>();
            for(Assignment assignment : course.get().getAssignments()) {
                assignments.add(assignment.getAssignmentId());
            }
            return assignments;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }
}
