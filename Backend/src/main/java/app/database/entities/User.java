package app.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @ManyToMany
    @JoinTable(
            name = "users_in_course",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private final Set<Course> userCourses = new HashSet<>();
    @ManyToMany(mappedBy = "eventUsers")
    private final Set<Event> userInvolvedEvents = new HashSet<>();
    @OneToMany(mappedBy = "eventOwner")
    private final Set<Event> userOwnsEvents = new HashSet<>();
    @OneToMany(mappedBy = "msgUser", cascade = CascadeType.ALL)
    private final Set<Message> msgHistory = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "contacts",
            joinColumns = @JoinColumn(name = "owner"),
            inverseJoinColumns = @JoinColumn(name = "contact")
    )
    private final Set<User> userContacts = new HashSet<>();
    @ManyToMany(mappedBy = "userContacts")
    private final Set<User> contactedBy = new HashSet<>();
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
    @Column(name = "phone_number")
    private String phoneNum;
    @Column(name = "email")
    private String email;

    // Constructors
    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Enum<Role> role, String phoneNum, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum<Role> getRole() {
        return this.role;
    }

    public void setRole(Enum<Role> role) {
        this.role = role;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public Set<User> getUserContacts() {
        return this.userContacts;
    }

    public Set<User> getContactedBy() {
        return this.contactedBy;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Collection functions
    public void addCourse(Course course) {
        this.userCourses.add(course);
    }

    public void delCourse(Course course) {
        this.userCourses.remove(course);
    }

    public void addContact(User user) {
        this.userContacts.add(user);
    }

    public void delContact(User user) {
        this.userContacts.remove(user);
    }

}