package app.service;

import app.database.Course;
import app.database.CourseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseDatabase courseDatabase;

    public List<Course> getAllCourses() {
        return courseDatabase.findAll();
    }

    public Optional<Course> getCourseById(Integer id) {
        return courseDatabase.findById(id);
    }

    public void addClass(Course course) {
        courseDatabase.save(course);
    }
}
