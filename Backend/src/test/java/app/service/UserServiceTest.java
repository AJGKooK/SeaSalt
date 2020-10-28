package app.service;

import app.database.User;
import app.service.database.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class UserServiceTest {

    UserService service;

    @Test
    public void canFindUser()
    {
        User user = new User("XYZ123", "XYZ123", "Test", "Test");
        service.saveUser(user);
        Optional<User> optionalUser = service.getUserByUsername("XYZ123");

        Assert.assertTrue(optionalUser.isPresent());
        Assert.assertEquals(optionalUser.get(), user);
    }
}
