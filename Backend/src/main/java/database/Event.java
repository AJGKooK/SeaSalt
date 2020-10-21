package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String desc;
    private Integer classId;
    private Integer time;

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public Integer getClassId() { return classId; }
    public Integer getTime() { return time; }

    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setClassId(Integer id) { classId = id; }
    public void setDueTime(Integer time) { this.time = time; }
}