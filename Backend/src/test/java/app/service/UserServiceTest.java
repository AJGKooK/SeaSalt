/*
package app.service;


import app.database.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class UserServiceTest {

    UserService service;

    @Test
    public void canFindUser()
    {
        User user = new User("XYZ123", "XYZ123");
        service.addUser(user);
        Optional<User> optionalUser = service.getUserByUsername("XYZ123");

        Assert.assertTrue(optionalUser.isPresent());
        Assert.assertEquals(optionalUser.get(), user);
    }

    @Test
    public void checkIfAdmin()
    {
        User user = new User("ugohar", "!MTH383$T");
        service.addUser(user);

        Assert.assertEquals(service.userLogin("ugohar", "!MTH383$T"), 0);
        Assert.assertEquals(service.userLogin("ugohar", "ManISuck"), 1);
        Assert.assertEquals(service.userLogin("handsoap", "LemmeGittemNiceNSqueaky"), 2);
    }

}
*/
