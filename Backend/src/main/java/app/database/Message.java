package app.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer msgId;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User msgUser;

    @Column(name = "timestamp", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgTimestamp;

    @Column(name = "content", precision = 2048)
    private String msgContent;

    // Constructors
    public Message() {}

    public Message(User user, String msg) {
        this.msgUser = user;
        this.msgContent = msg;
    }

    // Get functions
    public Integer getMsgId() {
        return this.msgId;
    }
    public User getMsgUser() {
        return this.msgUser;
    }
    public Date getMsgTimestamp() {
        return this.msgTimestamp;
    }
    public String getMsgContent() {
        return this.msgContent;
    }

    // Set functions
    public void setContent(String msg) {
        this.msgContent = msg;
    }
}
