package app.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private final String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private Integer role_id;

    @ManyToMany(mappedBy = "classUsers")
    private Set<Course> userCourses;

    public User() {
        this.username = String.valueOf(System.currentTimeMillis());
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Integer getRole() { return role_id; }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setRole(int role)
    {
        this.role_id = role;
    }
}