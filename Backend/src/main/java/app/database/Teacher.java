package app.database;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "teacher_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", precision = 2048)
    private String description;

    @OneToMany(mappedBy = "teachers")
    private Set<Course> courses;


    public Teacher()
    {
    }

    public Teacher(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
}
