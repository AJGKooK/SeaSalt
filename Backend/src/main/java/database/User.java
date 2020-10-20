package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private Integer role_id;
    private ArrayList<Integer> class_ids;
    private ArrayList<Integer> fin_hw_ids;


    /*
    Get functions
     */
    public Integer getID()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public Integer getRole() { return role_id; }

    public ArrayList<Integer> getClasses() { return class_ids; }

    public ArrayList<Integer> getFinishedHW() { return fin_hw_ids; }


    /*
    Set functions
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(int role)
    {
        this.role_id = role;
    }

    public void addClass(int id)
    {
        this.class_ids.add(id);
    }

    public void setClasses(ArrayList<Integer> ids)
    {
        this.class_ids = new ArrayList<Integer>(ids);
    }

    public void addFinishedHW(int id)
    {
        this.fin_hw_ids.add(id);
    }

    public void removeFinishedHW(int id)
    {
        this.fin_hw_ids.remove(id);
    }

    public void setFinishedHW(ArrayList<Integer> ids)
    {
        this.fin_hw_ids = new ArrayList<Integer>(ids);
    }
}