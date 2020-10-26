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

    @Test
    public void checkIfAdmin()
    {
        User user = new User("ugohar", "!MTH383$T");
        service.addUser(user);

        Assert.assertEquals(service.isAuthorized("ugohar", "!MTH383$T"), 0);
        Assert.assertEquals(service.isAuthorized("ugohar", "ManISuck"), 1);
        Assert.assertEquals(service.isAuthorized("handsoap", "LemmeGittemNiceNSqueaky"), 2);
    }

}
