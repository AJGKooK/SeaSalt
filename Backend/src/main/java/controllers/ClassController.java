package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

@Controller
@RequestMapping(path = "/class")
public class ClassController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/info")
    public @ResponseBody class (@RequestParam Integer id) {
        return
    }
}
