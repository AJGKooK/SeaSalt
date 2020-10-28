package app.controllers;

import app.database.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/login")
    public Integer login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if(user.isEmpty()) {
            return 2;
        } else if (user.get().getPassword().equals(password)) {
            return 0;
        } else {
            return 1;
        }
    }

    @PostMapping(path = "/register")
    public Integer register(@RequestParam String username, @RequestParam String password) {
        if (userService.getUserByUsername(username).isPresent()) {
            return 1;
        } else {
            User user = new User(username, password);
            userService.addUser(user);
            return 0;
        }
    }
}
