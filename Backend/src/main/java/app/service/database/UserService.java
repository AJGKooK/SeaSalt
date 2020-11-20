package app.service.database;

import app.database.entities.User;
import app.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDatabase userDatabase;

    public List<User> getAllUsers() {
        return userDatabase.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userDatabase.findById(username);
    }

    public void saveUser(User user) {
        userDatabase.save(user);
    }
}
