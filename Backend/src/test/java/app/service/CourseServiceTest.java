package app.service;

import app.database.Course;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

public class CourseServiceTest {

    CourseService service;

    @Mock
    Course course;

    @Test
    public void canFindCourse()
    {
        service.addClass(course);

        Optional<Course> optionalCourse = service.getCourseById(course.getCourseId());

        Assert.assertTrue(optionalCourse.isPresent());
        Assert.assertEquals(optionalCourse.get(), course);
    }
}