package service;

import database.User;
import database.UserDatabase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserDatabase userDatabase;

    public List<User> getAllUsers() {
        return userDatabase.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userDatabase.findById(username);
    }

    public void addUser(User user) {
        userDatabase.save(user);
    }

    public int isAuthorized(String username, String password) {
        Optional<User> user = getUserByUsername(username);
        if(user.isEmpty()) {
            return 2;
        } else if (user.get().getPassword().equals(password)) {
            return 0;
        } else {
            return 1;
        }
    }

   // public List<Class> getUserClasses(String username) {
        // TODO
   // }
}
