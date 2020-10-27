package app.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assignments")
public class Assignment implements Serializable {
    @Id
    @Column(name = "assignment_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "assignment_name")
    private String homeworkName;

    @Column(name = "assignment_desc", precision = 2048)
    private String homeworkDesc;

    @Column(name = "due_time")
    private Integer dueTime;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public String getHomeworkName() { return homeworkName; }
    public String getHomeworkDesc() { return homeworkDesc; }
    public Course getCourse() { return course; }
    public Integer getDueTime() { return dueTime; }

    public void setHomeworkName(String name) { this.homeworkName = name; }
    public void setHomeworkDesc(String desc) { this.homeworkDesc = desc; }
    public void setClassId(Course course) { this.course = course; }
    public void setDueTime(Integer dueTime) { this.dueTime = dueTime; }
}
