package app.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String homeworkName;
    private String homeworkDesc;
    private Integer classId;
    private Integer dueTime;

    public String getHomeworkName() { return homeworkName; }
    public String getHomeworkDesc() { return homeworkDesc; }
    public Integer getClassId() { return classId; }
    public Integer getDueTime() { return dueTime; }

    public void setHomeworkName(String name) { this.homeworkName = name; }
    public void setHomeworkDesc(String desc) { this.homeworkDesc = desc; }
    public void setClassId(Integer id) { classId = id; }
    public void setDueTime(Integer dueTime) { this.dueTime = dueTime; }
}
