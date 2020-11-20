package app.controllers;

import app.database.entities.Assignment;
import app.database.entities.Course;
import app.database.entities.Event;
import app.excpetions.NotFoundException;
import app.service.SecurityService;
import app.service.database.AssignmentService;
import app.service.database.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/assignment")
public class AssignmentController {

    @Autowired
    SecurityService securityService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    CourseService courseService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "/info")
    public ObjectNode info(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
            ObjectNode response = objectMapper.createObjectNode();
            response.put("id", assignment.get().getAssignmentId());
            response.put("name", assignment.get().getAssignmentName());
            response.put("desc", assignment.get().getAssignmentDesc());
            response.put("dueTime", assignment.get().getDueTime());
            response.put("course", assignment.get().getAssignmentCourse().getCourseId());
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/events")
    public ArrayList<Integer> events(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
            ArrayList<Integer> events = new ArrayList<>();
            for (Event event : assignment.get().getAssignmentEvents()) {
                events.add(event.getEventId());
            }
            return events;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/add")
    public Integer add(@RequestParam String username, @RequestParam String password, @RequestParam Integer courseId, @RequestParam String name, @RequestParam String desc, @RequestParam Integer due) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            securityService.isAuthorizedTeacherHttp(username, password, course.get());
            Assignment assignment = new Assignment(course.get(), name, desc, due);
            assignmentService.saveAssignment(assignment);
            return assignment.getAssignmentId();
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping(path = "/edit")
    public Integer edit(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam(required = false) String name,
                        @RequestParam(required = false) String desc, @RequestParam(required = false) Integer time) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            securityService.isAuthorizedTeacherHttp(username, password, assignment.get().getAssignmentCourse());
            if (name != null) {
                assignment.get().setAssignmentName(name);
            }
            if (desc != null) {
                assignment.get().setAssignmentDesc(desc);
            }
            if (time != null) {
                assignment.get().setDueTime(time);
            }
            assignmentService.saveAssignment(assignment.get());
            return assignment.get().getAssignmentId();
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }
}
