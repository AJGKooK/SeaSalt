package app.controllers;

import app.database.Assignment;
import app.database.Course;
import app.database.Event;
import app.excpetions.NotFoundException;
import app.service.*;
import app.service.database.AssignmentService;
import app.service.database.CourseService;
import app.service.database.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static app.service.FileUploadService.UploadType.*;

@Controller
@RequestMapping(path = "/upload")
public class UploadController {

    @Value("${app.scheme}")
    public String scheme;

    @Value("${app.hostname}")
    public String hostname;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    SecurityService securityService;

    @Autowired
    CourseService courseService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    EventService eventService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/user")
    public ObjectNode userUpload(@RequestParam String username, @RequestParam String password, @RequestParam("file") MultipartFile file) {
        String filename = fileUploadService.uploadFile(USER, securityService.isAuthorizedHttp(username, password).getUsername(), file);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("upload", this.scheme + this.hostname + "/static/upload/user/" + username + "/" + filename);
        return response;
    }

    @PostMapping("/user/profile")
    public ObjectNode profileUpload(@RequestParam String username, @RequestParam String password, @RequestParam("file") MultipartFile file) {
        securityService.isAuthorizedHttp(username, password);
        fileUploadService.profileUpload(username, file);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("upload", this.scheme + this.hostname + "/static/profiles/" + username + ".png");
        return response;
    }

    @PostMapping("/course")
    public ObjectNode courseUpload(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Course> course = courseService.getCourseById(id);
        if(course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            String filename = fileUploadService.uploadFile(COURSE, id.toString(), username, file);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("upload",this.scheme + this.hostname + "/static/upload/course/" + id + "/" + username + "/" + filename);
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/assignment")
    public ObjectNode assignmentUpload(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if(assignment.isPresent()) {
            securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
            String filename = fileUploadService.uploadFile(ASSIGNMENT, id.toString(), username, file);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("upload", this.scheme + this.hostname + "/static/upload/assignment/" + id + "/" + username + "/" + filename);
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/event")
    public ObjectNode eventUpload(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Event> event = eventService.getEventById(id);
        if(event.isPresent()) {
            securityService.isAuthorizedHttp(username, password, event.get());
            String filename = fileUploadService.uploadFile(ASSIGNMENT, id.toString(), username, file);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("upload", this.scheme + this.hostname + "/static/upload/assignment/" + id + "/" + username + "/" + filename);
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }
}
