package app.database;

import javax.persistence.*;
import java.io.Serializable;

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
    private Course course;

    public Assignment() {}

    public Assignment(Course course, String assignmentName, String assignmentDesc, Integer dueTime) {
        this.course = course;
        this.assignmentName = assignmentName;
        this.assignmentDesc = assignmentDesc;
        this.dueTime = dueTime;
    }

    public Integer getAssignmentId() { return this.assignmentId; }
    public String getAssignmentName() { return this.assignmentName; }
    public String getAssignmentDesc() { return this.assignmentDesc; }
    public Integer getDueTime() { return this.dueTime; }
    public Course getCourse() { return this.course; }

    public void setAssignmentName(String name) { this.assignmentName = name; }
    public void setAssignmentDesc(String desc) { this.assignmentDesc = desc; }
    public void setDueTime(Integer dueTime) { this.dueTime = dueTime; }
}
