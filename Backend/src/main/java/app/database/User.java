package app.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Enum<Role> role;

    @ManyToMany
    @JoinTable(
            name = "users_in_course",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> userCourses;

    @ManyToMany(mappedBy = "eventUsers")
    private Set<Event> userInvolvedEvents;

    @OneToMany(mappedBy = "eventOwner")
    private Set<Event> userOwnsEvents;

    // Constructors
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Get functions
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public Enum<Role> getRole() {
        return this.role;
    }
    public Set<Course> getUserCourses() {
        return this.userCourses;
    }
    public Set<Event> getUserInvolvedEvents() {
        return this.userInvolvedEvents;
    }
    public Set<Event> getUserOwnsEvents() {
        return this.userOwnsEvents;
    }

    // Set functions
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Enum<Role> role) {
        this.role = role;
    }

    // Collection functions
    public void addCourse(Course course) {
        this.userCourses.add(course);
    }
    public void deleteCourse(Course course) {
        this.userCourses.remove(course);
    }

}