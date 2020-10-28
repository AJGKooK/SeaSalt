package app.service;

import app.database.Course;
import app.database.Event;
import app.database.Message;
import app.database.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

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
        Assert.assertTrue(service.isAuthorizedHttp(user.getUsername(), user.getPassword()).equals(user));
    }

    @Test
    public void userInCourse()
    {
        user.addCourse(course);
        service.userService.saveUser(user);

        Assert.assertTrue(service.isAuthorizedHttp(user.getUsername(), user.getPassword(), course).equals(course));
    }

    @Test
    public void userInEvent()
    {
        event.addUser(user);
        service.userService.saveUser(user);

        Assert.assertTrue(service.isAuthorizedHttp(user.getUsername(), user.getPassword(), event).equals(event));
    }

    @Test
    public void userHasMessage()
    {
        user.getUserMessages().add(message);
        service.userService.saveUser(user);

        Assert.assertTrue(service.isAuthorizedHttp(user.getUsername(), user.getPassword(), message).equals(message));
    }
}