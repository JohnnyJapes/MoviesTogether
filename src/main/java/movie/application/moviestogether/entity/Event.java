package movie.application.moviestogether.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_event")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="title")
    private String title;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    @Column(name = "event_datetime")
    private String event_datetime;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventJoinUser> invitedUsers;


    public Event() {
    }



    public Event(int id, String title, User owner, String event_datetime, Movie movie, String location, String description, List<EventJoinUser> invitedUsers) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.event_datetime = event_datetime;
        this.movie = movie;
        this.location = location;
        this.description = description;
        this.invitedUsers = invitedUsers;
    }




    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_datetime() {
        return this.event_datetime;
    }

    public void setEvent_datetime(String event_datetime) {
        this.event_datetime = event_datetime;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EventJoinUser> getInvitedUsers() {
        return this.invitedUsers;
    }

    public void setInvitedUsers(List<EventJoinUser> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    
}
