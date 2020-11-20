package app.service;

import app.database.entities.Course;
import app.database.entities.Event;
import app.database.entities.Message;
import app.database.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

public class SecurityServiceTest {

    SecurityService service;

    @Mock
    User user;
    @Mock
    Event event;
    @Mock
    Message message;
    @Mock
    Course course;

    @Test
    public void userAuthorized()
    {
        service.userService.saveUser(user);
        Assert.assertEquals(service.isAuthorizedHttp(user.getUsername(), user.getPassword()), user);
    }

    @Test
    public void userInCourse()
    {
        user.addCourse(course);
        service.userService.saveUser(user);

        Assert.assertEquals(service.isAuthorizedHttp(user.getUsername(), user.getPassword(), course), course);
    }

    @Test
    public void userInEvent()
    {
        event.addUser(user);
        service.userService.saveUser(user);

        Assert.assertEquals(service.isAuthorizedHttp(user.getUsername(), user.getPassword(), event), event);
    }

    @Test
    public void userHasMessage()
    {
        user.getUserMessages().add(message);
        service.userService.saveUser(user);

        Assert.assertEquals(service.isAuthorizedHttp(user.getUsername(), user.getPassword(), message), message);
    }
}