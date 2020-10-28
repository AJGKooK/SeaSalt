package app.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event implements Serializable {
    @Id
    @Column(name = "event_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_desc")
    private String eventDesc;

    @Column(name = "event_time")
    private Integer eventTime;

    @ManyToMany
    @JoinTable(
            name = "users_in_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private Set<User> eventUsers;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course eventCourse;

    @ManyToOne
    @JoinColumn(name = "username")
    private User eventOwner;

    @ManyToMany
    @JoinTable(
            name = "assignemts_in_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "assignment_id")
    )
    private Set<Assignment> eventAssignments;

    // Constructors
    public Event() {}

    public Event(String eventName, String eventDesc, Integer eventTime) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
        this.eventUsers = new HashSet<>();
        this.eventAssignments = new HashSet<>();
    }

    public Event(String eventName, String eventDesc, Integer eventTime, Course eventCourse) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
        this.eventCourse = eventCourse;
        this.eventUsers = new HashSet<>();
        this.eventAssignments = new HashSet<>();
    }

    public Event(String eventName, String eventDesc, Integer eventTime, User eventOwner) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
        this.eventOwner = eventOwner;
        this.eventUsers = new HashSet<>();
        this.eventAssignments = new HashSet<>();
    }

    public Event(String eventName, String eventDesc, Integer eventTime, User eventOwner, Course eventCourse) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
        this.eventOwner = eventOwner;
        this.eventCourse = eventCourse;
        this.eventUsers = new HashSet<>();
        this.eventAssignments = new HashSet<>();
    }

    // Get functions
    public Integer getEventId() {
        return this.eventId;
    }
    public String getEventName() {
        return this.eventName;
    }
    public String getEventDesc() {
        return this.eventDesc;
    }
    public Integer getEventTime() {
        return this.eventTime;
    }
    public Set<User> getEventUsers() {
        return this.eventUsers;
    }
    public Course getEventCourse() {
        return this.eventCourse;
    }
    public User getEventOwner() {
        return this.eventOwner;
    }
    public Set<Assignment> getEventAssignments() {
        return this.eventAssignments;
    }

    // Set functions
    public void setEventName(String name) {
        this.eventName = name;
    }
    public void setEventDesc(String desc) {
        this.eventDesc = desc;
    }
    public void setEventTime(Integer time) {
        this.eventTime = time;
    }
    public void setEventCourse(Course course) {
        this.eventCourse = course;
    }
    public void setEventOwner(User user) {
        this.eventOwner = user;
    }

    // Collection functions
    public void addUser(User user) {
        this.eventUsers.add(user);
    }
    public void delUser(User user) {
        this.eventUsers.remove(user);
    }
    public void addAssignment(Assignment assignment) {
        this.eventAssignments.add(assignment);
    }
    public void delAssignment(Assignment assignment) {
        this.eventAssignments.add(assignment);
    }
}
