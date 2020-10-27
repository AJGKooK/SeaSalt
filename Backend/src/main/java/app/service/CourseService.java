package app.service;

import app.database.CourseDatabase;
import app.database.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseDatabase courseDatabase;

    public List<Course> getAllClasses() {
        return courseDatabase.findAll();
    }

    public Optional<Course> getClassById(Integer id) {
        return courseDatabase.findById(id);
    }

    public void addClass(Course course) {
        courseDatabase.save(course);
    }
}
