package service;

import database.User;
import database.UserDatabase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserDatabase userDatabase;

    public List<User> getAllUsers() {
        return userDatabase.findAll();
    }

    public User getUserByUsername(String username) {
        return userDatabase.findByUsername(username);
    }

    public void addUser(User user) {
        userDatabase.save(user);
    }

    public int isAuthorized(String username, String password) {
        User user = getUserByUsername(username);
        if(user == null) {
            return 2;
        } else if (user.getPassword().equals(password)) {
            return 0;
        } else {
            return 1;
        }
    }
}
