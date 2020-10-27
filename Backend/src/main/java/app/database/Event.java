package app.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String eventName;
    private String eventDesc;
    private Integer classId;
    private Integer eventTime;

    public String getEventName() { return eventName; }
    public String getEventDesc() { return eventDesc; }
    public Integer getClassId() { return classId; }
    public Integer getEventTime() { return eventTime; }

    public void setEventName(String name) { this.eventName = name; }
    public void setEventDesc(String desc) { this.eventDesc = desc; }
    public void setClassId(Integer id) { classId = id; }
    public void setDueTime(Integer time) { this.eventTime = time; }
}
