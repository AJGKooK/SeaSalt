package app.controllers;

import app.database.entities.Assignment;
import app.database.entities.Course;
import app.database.entities.Event;
import app.excpetions.BadRequestException;
import app.excpetions.NotFoundException;
import app.service.FileService;
import app.service.SecurityService;
import app.service.database.AssignmentService;
import app.service.database.CourseService;
import app.service.database.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

import static app.service.FileService.UploadType.*;

@RestController
@RequestMapping(path = "/file")
public class FileController {

    @Value("${app.scheme}")
    public String scheme;

    @Value("${app.hostname}")
    public String hostname;

    @Autowired
    FileService fileService;

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

    @GetMapping("/user/list")
    public ArrayList<String> userList(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String view) {
        if (view != null) {
            securityService.isAuthorizedAdminHttp(username, password);
            return getUserStrings(view);
        } else {
            securityService.isAuthorizedHttp(username, password);
            return getUserStrings(username);
        }
    }


    @GetMapping("/course/list")
    public ArrayList<String> courseList(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam(required = false) String view) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            if (view != null) {
                securityService.isAuthorizedTeacherHttp(username, password, course.get());
                return getCourseStrings(view, id);
            } else {
                securityService.isAuthorizedHttp(username, password, course.get());
                return getCourseStrings(username, id);
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }


    @GetMapping("/assignment/list")
    public ArrayList<String> assignmentList(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam(required = false) String view) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            if (view != null) {
                securityService.isAuthorizedTeacherHttp(username, password, assignment.get());
                return getAssignmentStrings(id, view);
            } else {
                securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
                return getAssignmentStrings(id, username);
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }


    @GetMapping("/event/list")
    public ArrayList<String> eventList(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam(required = false) String view) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            if (view != null) {
                if (event.get().getEventCourse() != null) {
                    securityService.isAuthorizedTeacherHttp(username, password, event.get().getEventCourse());
                } else {
                    securityService.isAuthorizedOwnerHttp(username, password, event.get());
                }
                return getEventStrings(username, id, view);
            } else {
                return getEventStrings(username, id, username);
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/user/upload")
    public ObjectNode userUpload(@RequestParam String username, @RequestParam String password, @RequestParam("file") MultipartFile file) {
        String filename = fileService.uploadFile(USER, securityService.isAuthorizedHttp(username, password).getUsername(), file);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("upload", this.scheme + this.hostname + "/static/upload/user/" + username + "/" + filename);
        return response;
    }

    @PostMapping("/user/profile/upload")
    public ObjectNode profileUpload(@RequestParam String username, @RequestParam String password, @RequestParam("file") MultipartFile file) {
        securityService.isAuthorizedHttp(username, password);
        fileService.profileUpload(username, file);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("upload", this.scheme + this.hostname + "/static/profiles/" + username + ".png");
        return response;
    }

    @PostMapping("/course")
    public ObjectNode courseUpload(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            securityService.isAuthorizedHttp(username, password, course.get());
            String filename = fileService.uploadFile(COURSE, id.toString(), username, file);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("upload", this.scheme + this.hostname + "/static/upload/course/" + id + "/" + username + "/" + filename);
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/assignment/upload")
    public ObjectNode assignmentUpload(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
            String filename = fileService.uploadFile(ASSIGNMENT, id.toString(), username, file);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("upload", this.scheme + this.hostname + "/static/upload/assignment/" + id + "/" + username + "/" + filename);
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/event/upload")
    public ObjectNode eventUpload(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            securityService.isAuthorizedHttp(username, password, event.get());
            String filename = fileService.uploadFile(EVENT, id.toString(), username, file);
            ObjectNode response = objectMapper.createObjectNode();
            response.put("upload", this.scheme + this.hostname + "/static/upload/event/" + id + "/" + username + "/" + filename);
            return response;
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/user/delete")
    public boolean deleteUserFile(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam(required = false) String delete) {
        if (name.contains("/")) {
            throw new BadRequestException();
        }
        if (delete != null) {
            securityService.isAuthorizedAdminHttp(username, password);
            if (fileService.deleteFile(USER, delete, name)) {
                return true;
            } else {
                throw new NotFoundException();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            if (fileService.deleteFile(USER, username, name)) {
                return true;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @PostMapping("/user/profile/delete")
    public boolean deleteUserFile(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String delete) {
        if (delete != null) {
            securityService.isAuthorizedAdminHttp(username, password);
            if (fileService.deleteProfile(delete)) {
                return true;
            } else {
                throw new NotFoundException();
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            if (fileService.deleteProfile(username)) {
                return true;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @PostMapping("/course/delete")
    public boolean deleteCourseFile(@RequestParam String username, @RequestParam String password, @RequestParam String name,
                                    @RequestParam Integer id, @RequestParam(required = false) String delete) {
        if (name.contains("/")) {
            throw new BadRequestException();
        }
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            if (delete != null) {
                securityService.isAuthorizedTeacherHttp(username, password, course.get());
                if (fileService.deleteFile(COURSE, id.toString(), delete, name)) {
                    return true;
                } else {
                    throw new NotFoundException();
                }
            } else {
                securityService.isAuthorizedHttp(username, password, course.get());
                if (fileService.deleteFile(COURSE, id.toString(), username, name)) {
                    return true;
                } else {
                    throw new NotFoundException();
                }
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/assignment/delete")
    public boolean deleteAssignmentFile(@RequestParam String username, @RequestParam String password, @RequestParam String name,
                                        @RequestParam Integer id, @RequestParam(required = false) String delete) {
        if (name.contains("/")) {
            throw new BadRequestException();
        }
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            if (delete != null) {
                securityService.isAuthorizedTeacherHttp(username, password, assignment.get());
                if (fileService.deleteFile(ASSIGNMENT, id.toString(), delete, name)) {
                    return true;
                } else {
                    throw new NotFoundException();
                }
            } else {
                securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
                if (fileService.deleteFile(ASSIGNMENT, id.toString(), username, name)) {
                    return true;
                } else {
                    throw new NotFoundException();
                }
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @PostMapping("/event/delete")
    public boolean deleteEventFile(@RequestParam String username, @RequestParam String password, @RequestParam String name,
                                   @RequestParam Integer id, @RequestParam(required = false) String delete) {
        if (name.contains("/")) {
            throw new BadRequestException();
        }
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            if (delete != null) {
                if (event.get().getEventCourse() != null) {
                    securityService.isAuthorizedTeacherHttp(username, password, event.get().getEventCourse());
                } else {
                    securityService.isAuthorizedOwnerHttp(username, password, event.get());
                }
                if (fileService.deleteFile(EVENT, id.toString(), delete, name)) {
                    return true;
                } else {
                    throw new NotFoundException();
                }
            } else {
                securityService.isAuthorizedHttp(username, password, event.get());
                if (fileService.deleteFile(EVENT, id.toString(), username, name)) {
                    return true;
                } else {
                    throw new NotFoundException();
                }
            }
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    private ArrayList<String> getUserStrings(@RequestParam(required = false) String view) {
        String[] files = fileService.getFiles(USER, view);
        ArrayList<String> urls = new ArrayList<>(files.length);
        for (String filename : files) {
            urls.add(this.scheme + this.hostname + "/static/upload/user/" + view + "/" + filename);
        }
        return urls;
    }

    private ArrayList<String> getCourseStrings(@RequestParam String username, @RequestParam Integer id) {
        String[] files = fileService.getFiles(COURSE, id.toString(), username);
        ArrayList<String> urls = new ArrayList<>(files.length);
        for (String filename : files) {
            urls.add(this.scheme + this.hostname + "/static/upload/course/" + id + "/" + username + "/" + filename);
        }
        return urls;
    }

    private ArrayList<String> getAssignmentStrings(@RequestParam Integer id, @RequestParam(required = false) String view) {
        String[] files = fileService.getFiles(ASSIGNMENT, id.toString(), view);
        ArrayList<String> urls = new ArrayList<>(files.length);
        for (String filename : files) {
            urls.add(this.scheme + this.hostname + "/static/upload/assignment/" + id + "/" + view + "/" + filename);
        }
        return urls;
    }

    private ArrayList<String> getEventStrings(@RequestParam String username, @RequestParam Integer id, @RequestParam(required = false) String view) {
        String[] files = fileService.getFiles(EVENT, id.toString(), view);
        ArrayList<String> urls = new ArrayList<>(files.length);
        for (String filename : files) {
            urls.add(this.scheme + this.hostname + "/static/upload/event/" + id + "/" + username + "/" + filename);
        }
        return urls;
    }

}
