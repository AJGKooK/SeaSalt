package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String desc;
    private Integer teacher;
    private String classTime;
    private ArrayList<Integer> hwIds;
    private ArrayList<Integer> eventIds;

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public Integer getTeacherID() { return teacher; }
    public String getClassTime() { return classTime; }
    public ArrayList<Integer> getHwIds() { return hwIds; }
    public ArrayList<Integer> getEventIds() { return eventIds; }

    public void setName(String pName) { name = pName; }
    public void setDesc(String pDesc) { desc = pDesc; }
    public void setTeacher(Integer teachID) { teacher = teachID; }
    public void setClassTime(String newTime) { classTime = newTime; }
    public void addHwIds(Integer hw_id) { hwIds.add(hw_id); }
    public void removeHwIds(Integer hw_id) { hwIds.remove(hw_id); }
    public void addEventIds(Integer ev_id) { eventIds.add(ev_id); }
    public void removeEventIds(Integer ev_id) { eventIds.remove(ev_id); }



}
