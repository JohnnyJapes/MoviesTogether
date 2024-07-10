package movie.application.moviestogether.entity;

import java.util.List;

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
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "tmdb_id")
    private int tmdb_id;

    @Column(name = "watchmode_id")
    private int watchmode_id;

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "description")
    private String description;


    @OneToMany
    @JoinColumn(name = "movie_id",  referencedColumnName = "id")
    private List<MovieSource> sources;




    public Movie() {
    }


    public Movie(int id, int tmdb_id, int watchmode_id, String title, String director, String description, List<MovieSource> sources) {
        this.id = id;
        this.tmdb_id = tmdb_id;
        this.watchmode_id = watchmode_id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.sources = sources;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTmdb_id() {
        return this.tmdb_id;
    }

    public void setTmdb_id(int tmdb_id) {
        this.tmdb_id = tmdb_id;
    }

    public int getWatchmode_id() {
        return this.watchmode_id;
    }

    public void setWatchmode_id(int watchmode_id) {
        this.watchmode_id = watchmode_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieSource> getSources() {
        return this.sources;
    }

    public void setSources(List<MovieSource> sources) {
        this.sources = sources;
    }
    
    
}
