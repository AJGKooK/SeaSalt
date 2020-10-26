package service;

import database.User;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {

    UserService service;

    @Test
    public void canFindUser()
    {
        User user = new User("XYZ123", "XYZ123");
        service.addUser(user);

        Assert.assertTrue(service.getUserByUsername("XYZ123").equals(user));
    }

}
