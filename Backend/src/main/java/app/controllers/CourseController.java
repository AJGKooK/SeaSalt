package app.controllers;

import app.database.entities.Assignment;
import app.database.entities.Course;
import app.database.entities.Event;
import app.database.entities.User;
import app.excpetions.NotFoundException;
import app.service.SecurityService;
import app.service.database.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (course.isPresent()) {
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

    @GetMapping(path = "/teachers")
    public ArrayList<String> teachers(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            ArrayList<String> teachers = new ArrayList<>();
            for (User user : course.get().getTeachers()) {
                teachers.add(user.getUsername());
            }
            return teachers;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/users")
    public ArrayList<String> users(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            ArrayList<String> users = new ArrayList<>();
            for (User user : course.get().getCourseUsers()) {
                users.add(user.getUsername());
            }
            return users;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/assignments")
    public ArrayList<Integer> assignments(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            ArrayList<Integer> assignments = new ArrayList<>();
            for (Assignment assignment : course.get().getAssignments()) {
                assignments.add(assignment.getAssignmentId());
            }
            return assignments;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/events")
    public ArrayList<Integer> events(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            ArrayList<Integer> events = new ArrayList<>();
            for (Event event : course.get().getCourseEvents()) {
                events.add(event.getEventId());
            }
            return events;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/add")
    public Integer add(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String desc, @RequestParam String time) {
        securityService.isAuthorizedAdminHttp(username, password);
        Course course = new Course(name, desc, time);
        courseService.saveCourse(course);
        return course.getCourseId();
    }

    @PostMapping(path = "/edit")
    public Integer edit(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam(required = false) String name,
                        @RequestParam(required = false) String desc, @RequestParam(required = false) String time) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            securityService.isAuthorizedTeacherHttp(username, password, course.get());
            if (name != null) {
                course.get().setCourseName(name);
            }
            if (desc != null) {
                course.get().setCourseDesc(desc);
            }
            if (time != null) {
                course.get().setCourseTime(time);
            }
            courseService.saveCourse(course.get());
            return course.get().getCourseId();
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }
}
