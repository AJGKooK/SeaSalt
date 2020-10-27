package app.service;

import app.controllers.ForbiddenException;
import app.database.Course;
import app.database.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    UserService userService;

    public boolean isAuthorized(User user, String password) {
        return user.getPassword().equals(password);
    }

    public void isAuthorizedHttp(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isPresent()) {
            if(!isAuthorized(user.get(), password)) {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    public void isAuthorizedHttp(String username, String password, Course course) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isPresent()) {
            if(!(isAuthorized(user.get(), password)) || !(user.get().getUserCourses().contains(course))) {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }
}
