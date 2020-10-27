package app.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "course")
    private Set<Assignment> assignments;

    public Course() {}

    public Course(String courseName, String courseDesc, String courseTime) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.courseTime = courseTime;
    }

    public Integer getCourseId() { return this.courseId; }
    public String getCourseName() { return this.courseName; }
    public String getCourseDesc() { return this.courseDesc; }
    public String getCourseTime() { return this.courseTime; }
    public Set<User> getCourseUsers() { return this.courseUsers; }
    public Set<Assignment> getAssignments() { return this.assignments; }

    public Set<User> getTeachers() {
        Set<User> teachers = new HashSet<>();
        for (User user : this.courseUsers) {
            if(user.getRole() == 1) {
                teachers.add(user);
            }
        }
        return teachers;
    }

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseDesc(String courseDesc) { this.courseDesc = courseDesc; }
    public void setCourseTime(String courseTime) { this.courseTime = courseTime; }
}
