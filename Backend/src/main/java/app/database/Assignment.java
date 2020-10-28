package app.database;

import javax.persistence.*;
import java.io.Serializable;
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
    public Assignment() {}

    public Assignment(Course assignmentCourse, String assignmentName, String assignmentDesc, Integer dueTime) {
        this.assignmentCourse = assignmentCourse;
        this.assignmentName = assignmentName;
        this.assignmentDesc = assignmentDesc;
        this.dueTime = dueTime;
    }

    // Get functions
    public Integer getAssignmentId() {
        return this.assignmentId;
    }
    public String getAssignmentName() {
        return this.assignmentName;
    }
    public String getAssignmentDesc() {
        return this.assignmentDesc;
    }
    public Integer getDueTime() {
        return this.dueTime;
    }
    public Course getAssignmentCourse() {
        return this.assignmentCourse;
    }
    public Set<Event> getAssignmentEvents() {
        return this.assignmentEvents;
    }

    // Set functions
    public void setAssignmentName(String name) {
        this.assignmentName = name;
    }
    public void setAssignmentDesc(String desc) {
        this.assignmentDesc = desc;
    }
    public void setDueTime(Integer dueTime) {
        this.dueTime = dueTime;
    }
}
