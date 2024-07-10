package movie.application.moviestogether.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "watchLists")
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private int id;

    @Column(name = "user_id")
    private int userID;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "list_id", referencedColumnName = "list_id")
    private List<ListItem> movies;



    public WatchList() {
    }


    public WatchList(int id, int userID, String name, List<ListItem> movies) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.movies = movies;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListItem> getMovies() {
        return this.movies;
    }

    public void setMovies(List<ListItem> movies) {
        this.movies = movies;
    }
    
}
