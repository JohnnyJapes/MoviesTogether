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
@Table(name = "movie_source")
public class MovieSource {


    @Id
    @Column(name = "movie_id")
    private int movieID;

    @Id
    @OneToOne
    @JoinColumn(name = "source_id")
    private Source source;


    @Column(name = "tmdb_id")
    private int tmdb_id;

    @Column(name = "watchmode_id")
    private int watchmode_id;

    @Column(name = "type")
    private String type;


    @Column(name = "region")
    private String region;

    @Column(name = "web_url")
    private String webURL;

    @JoinColumn(name = "format_id")
    private Format format;

    @Column(name = "price")
    private double price;

    @Column(name = "seasons")
    private int seasons;

    @Column(name = "episodes")
    private int episodes;



    public MovieSource() {
    }


    public MovieSource(int movieID, Source source, int tmdb_id, int watchmode_id, String type, String region, String webURL, Format format, double price, int seasons, int episodes) {
        this.movieID = movieID;
        this.source = source;
        this.tmdb_id = tmdb_id;
        this.watchmode_id = watchmode_id;
        this.type = type;
        this.region = region;
        this.webURL = webURL;
        this.format = format;
        this.price = price;
        this.seasons = seasons;
        this.episodes = episodes;
    }


    public int getMovieID() {
        return this.movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public Source getSource() {
        return this.source;
    }

    public void setSource(Source source) {
        this.source = source;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWebURL() {
        return this.webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public Format getFormat() {
        return this.format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeasons() {
        return this.seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
    
}
