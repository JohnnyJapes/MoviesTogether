package movie.application.moviestogether.entity;

import org.hibernate.annotations.DialectOverride.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_event")
public class EventJoinUser {


    @Id
    @Column(name = "event_id")
    private int eventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;



    public EventJoinUser() {
    }




    public EventJoinUser(int eventId, User user, Status status) {
        this.eventId = eventId;
        this.user = user;
        this.status = status;
    }




    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    
}
