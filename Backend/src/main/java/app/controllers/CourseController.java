package app.controllers;

import app.database.Course;
import app.service.CourseService;
import app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService classService;

    @PostMapping(path = "/info")
    public @ResponseBody
    Course UniClass (@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        if(userService.isAuthorized(username, password)) {
            Optional<Course> uniClass = classService.getClassById(id);
            if(uniClass.isEmpty()) {
                throw new NotFoundException();
            } else {
                return uniClass.get();
            }
        }
        throw new ForbiddenException();
    }
}
