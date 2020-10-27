package app.service;

import app.database.Course;
import app.database.Event;
import app.database.User;
import app.excpetions.ForbiddenException;
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

    public User isAuthorizedHttp(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isPresent()) {
            if(!isAuthorized(user.get(), password)) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedHttp(String username, String password, Course course) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isPresent()) {
            if(!(isAuthorized(user.get(), password)) || !(user.get().getUserCourses().contains(course))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedHttp(String username, String password, Event event) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isPresent()) {
            if(!(isAuthorized(user.get(), password)) || !(user.get().getUserInvolvedEvents().contains(event))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }
}


