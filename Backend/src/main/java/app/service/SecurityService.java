package app.service;

import app.database.entities.*;
import app.excpetions.ForbiddenException;
import app.service.database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static app.database.entities.Role.ADMIN;

@Service
public class SecurityService {

    @Autowired
    UserService userService;

    private boolean isNotAuthorized(User user, String password) {
        return !user.getPassword().equals(password);
    }

    private boolean isNotAdmin(User user) {
        return user.getRole() != ADMIN;
    }

    public User isAuthorizedHttp(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password)) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedHttp(String username, String password, Course course) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || (!user.get().getUserCourses().contains(course) && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedHttp(String username, String password, Event event) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || (!user.get().getUserInvolvedEvents().contains(event) && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedHttp(String username, String password, Message message) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || (message.getMsgUser() != user.get() && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedOwnerHttp(String username, String password, Event event) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || (event.getEventOwner() != user.get() && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedOwnerHttp(String username, String password, Event event, Course course) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || ((event.getEventOwner() != user.get() || !user.get().getUserCourses().contains(course))
                    && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedAdminHttp(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || isNotAdmin(user.get())) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedTeacherHttp(String username, String password, Course course) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || (!course.getTeachers().contains(user.get()) && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }

    public User isAuthorizedTeacherHttp(String username, String password, Assignment assignment) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            if (isNotAuthorized(user.get(), password) || (!assignment.getAssignmentCourse().getTeachers().contains(user.get()) && isNotAdmin(user.get()))) {
                throw new ForbiddenException();
            }
            return user.get();
        } else {
            throw new ForbiddenException();
        }
    }
}


