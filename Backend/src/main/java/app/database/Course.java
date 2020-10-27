package app.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "course_name")
    private String className;

    @Column(name = "course_desc", precision = 2048)
    private String classDesc;

    // TODO: foreign to key user
    @Column(name = "teacher")
    private Integer teacher;

    @Column(name = "course_time")
    private String classTime;

    @ManyToMany
    @JoinTable(
            name = "students_in_course",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<User> students;

    @OneToMany(mappedBy = "courses")
    private Set<Assignment> assignments;

    public String getCassName() { return className; }
    public String getClassDesc() { return classDesc; }
    public Integer getTeacherID() { return teacher; }
    public String getClassTime() { return classTime; }

    public void setClassName(String pName) { className = pName; }
    public void setClassDesc(String pDesc) { classDesc = pDesc; }
    public void setTeacher(Integer teachID) { teacher = teachID; }
    public void setClassTime(String newTime) { classTime = newTime; }
}
