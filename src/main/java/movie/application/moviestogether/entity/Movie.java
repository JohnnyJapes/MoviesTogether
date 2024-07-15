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
    private int tmdbID;

    @Column(name = "watchmode_id")
    private int watchmode_id;

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "description")
    private String description;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "year")
    private int year;


    @OneToMany
    @JoinColumn(name = "movie_id",  referencedColumnName = "id")
    private List<MovieSource> sources;




    public Movie() {
    }


    public Movie(int id, int tmdb_id, int watchmode_id, String title, String director, String description, String posterPath, List<MovieSource> sources) {
        this.id = id;
        this.tmdbID = tmdb_id;
        this.watchmode_id = watchmode_id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.posterPath = posterPath;
        this.sources = sources;
    }


    public Movie(int id, int tmdbID, int watchmode_id, String title, String director, String description, String posterPath, int year, List<MovieSource> sources) {
        this.id = id;
        this.tmdbID = tmdbID;
        this.watchmode_id = watchmode_id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.posterPath = posterPath;
        this.year = year;
        this.sources = sources;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTmdbID() {
        return this.tmdbID;
    }

    public void setTmdbID(int tmdb_id) {
        this.tmdbID = tmdb_id;
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

    public String getPosterPath() {
        return this.posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    
    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
