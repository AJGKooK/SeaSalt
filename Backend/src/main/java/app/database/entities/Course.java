package app.database.entities;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static app.database.entities.Role.TEACHER;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_desc", precision = 2048)
    private String courseDesc;

    @Column(name = "course_time")
    private String courseTime;

    @ManyToMany(mappedBy = "userCourses")
    private Set<User> courseUsers;

    @OneToMany(mappedBy = "assignmentCourse")
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "eventCourse")
    private Set<Event> courseEvents;
    
    // Constructors
    public Course() {
    }

    public Course(String courseName, String courseDesc, String courseTime) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.courseTime = courseTime;
        this.courseUsers = new HashSet<>();
        this.assignments = new HashSet<>();
        this.courseEvents = new HashSet<>();
    }

    // Get functions
    public Integer getCourseId() {
        return this.courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    // Set functions
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return this.courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCourseTime() {
        return this.courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Set<User> getCourseUsers() {
        return this.courseUsers;
    }

    public Set<Assignment> getAssignments() {
        return this.assignments;
    }

    public Set<Event> getCourseEvents() {
        return this.courseEvents;
    }
    
    public Set<User> getTeachers() {
        Set<User> teachers = new HashSet<>();
        for (User user : this.courseUsers) {
            if (user.getRole() == TEACHER) {
                teachers.add(user);
            }
        }
        return teachers;
    }
}
