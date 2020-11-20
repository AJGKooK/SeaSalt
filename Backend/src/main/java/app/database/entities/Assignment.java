package app.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assignments")
public class Assignment implements Serializable {
    @Id
    @Column(name = "assignment_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer assignmentId;

    @Column(name = "assignment_name")
    private String assignmentName;

    @Column(name = "assignment_desc", precision = 2048)
    private String assignmentDesc;

    @Column(name = "due_time")
    private Integer dueTime;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course assignmentCourse;

    @ManyToMany(mappedBy = "eventAssignments")
    private Set<Event> assignmentEvents;

    // Constructors
    public Assignment() {
    }

    public Assignment(Course assignmentCourse, String assignmentName, String assignmentDesc, Integer dueTime) {
        this.assignmentCourse = assignmentCourse;
        this.assignmentName = assignmentName;
        this.assignmentDesc = assignmentDesc;
        this.dueTime = dueTime;
        this.assignmentEvents = new HashSet<>();
    }

    // Get functions
    public Integer getAssignmentId() {
        return this.assignmentId;
    }

    public String getAssignmentName() {
        return this.assignmentName;
    }

    // Set functions
    public void setAssignmentName(String name) {
        this.assignmentName = name;
    }

    public String getAssignmentDesc() {
        return this.assignmentDesc;
    }

    public void setAssignmentDesc(String desc) {
        this.assignmentDesc = desc;
    }

    public Integer getDueTime() {
        return this.dueTime;
    }

    public void setDueTime(Integer dueTime) {
        this.dueTime = dueTime;
    }

    public Course getAssignmentCourse() {
        return this.assignmentCourse;
    }

    public Set<Event> getAssignmentEvents() {
        return this.assignmentEvents;
    }
}
