package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class UniClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String desc;
    private Integer teacher;
    private String classTime;

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public Integer getTeacherID() { return teacher; }
    public String getClassTime() { return classTime; }

    public void setName(String pName) { name = pName; }
    public void setDesc(String pDesc) { desc = pDesc; }
    public void setTeacher(Integer teachID) { teacher = teachID; }
    public void setClassTime(String newTime) { classTime = newTime; }
}
