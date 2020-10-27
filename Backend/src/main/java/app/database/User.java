package app.database;

import app.service.CourseService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private Integer role_id;

    @ManyToMany
    @JoinTable(
            name = "users_in_course",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> userCourses;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public Integer getRole() { return this.role_id; }
    public Set<Course> getUserCourses() { return userCourses; }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setRole(int role)
    {
        this.role_id = role;
    }

    public void addCourse(Course course) {
        this.userCourses.add(course);
    }

    public void deleteCourse(Course course) {
        this.userCourses.remove(course);
    }

}