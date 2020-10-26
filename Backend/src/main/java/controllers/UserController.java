package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import database.User;
import service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/login")
    public @ResponseBody
    int login (@RequestParam String username, @RequestParam String password) {
        return userService.userLogin(username, password);
    }

    @PostMapping(path = "/register")
    public @ResponseBody int register (@RequestParam String username, @RequestParam String password) {
        if (userService.getUserByUsername(username).isPresent()) {
            return 1;
        } else {
            User user = new User(username, password);
            userService.addUser(user);
            return 0;
        }
    }
}
