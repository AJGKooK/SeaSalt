package app.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @Column(name="username", unique = true, nullable = false)
    private final String username;

    @Column(name="password")
    private String password;

    @Column(name="role_id")
    private Integer role_id;

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


    /*
    Get functions
     */
    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public Integer getRole() { return role_id; }


    /*
    Set functions
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(int role)
    {
        this.role_id = role;
    }
}