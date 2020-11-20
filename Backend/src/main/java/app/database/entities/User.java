package app.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private Enum<Role> role;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

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

    @OneToMany(mappedBy = "msgUser", cascade = CascadeType.ALL)
    private Set<Message> msgHistory;

    // Constructors
    public User() {}

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCourses = new HashSet<>();
        this.userInvolvedEvents = new HashSet<>();
        this.userOwnsEvents = new HashSet<>();
        this.msgHistory = new HashSet<>();
    }

    public User(String username, String password, String firstName, String lastName, Enum<Role> role) {
        this(username, password, firstName, lastName);
        this.role = role;
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
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
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
    public Set<Message> getUserMessages() {
        return this.msgHistory;
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
    public void delCourse(Course course) {
        this.userCourses.remove(course);
    }

}