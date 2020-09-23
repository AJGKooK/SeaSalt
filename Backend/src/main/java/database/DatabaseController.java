package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//TODO change URL semantics
@Controller
@RequestMapping(path="/database")
public class DatabaseController {
    @Autowired
    private UserDatabase userDatabase;

    @PostMapping(path="/check")
    public @ResponseBody User checkUser (@RequestParam String username
            , @RequestParam String password){
        List<User> users = userDatabase.findAll();
        for(User user : users) {
            if(user.getUsername().equals(username))
            {
                if(user.getPassword().equals(password)) return user;
                else return null;
            }
        }

        return null;
    }

    @PostMapping(path="/add")
    public @ResponseBody boolean addUser(@RequestParam String username
            , @RequestParam String password){

        List<User> users = userDatabase.findAll();

        for(User user : users)
            if(user.getUsername().equals(username)) return false;

        User addedUser = new User();

        addedUser.setPassword(password);
        addedUser.setUsername(username);

        userDatabase.save(addedUser);
        return true;
    }
}