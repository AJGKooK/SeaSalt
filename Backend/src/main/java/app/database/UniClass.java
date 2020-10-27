package app.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UniClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String className;
    private String classDesc;
    private Integer teacher;
    private String classTime;

    public String getClassName() { return className; }
    public String getClassDesc() { return classDesc; }
    public Integer getTeacherID() { return teacher; }
    public String getClassTime() { return classTime; }

    public void setClassName(String pName) { className = pName; }
    public void setClassDesc(String pDesc) { classDesc = pDesc; }
    public void setTeacher(Integer teachID) { teacher = teachID; }
    public void setClassTime(String newTime) { classTime = newTime; }
}
