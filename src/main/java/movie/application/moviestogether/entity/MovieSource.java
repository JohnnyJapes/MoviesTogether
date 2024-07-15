package movie.application.moviestogether.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "movie_source")
public class MovieSource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "source_id")
    private Source source;

    @Column(name = "movie_id")
    private int movieID;


    @Column(name = "type")
    private String type;


    @Column(name = "region")
    private String region;

    @Column(name = "web_url")
    private String webURL;

    @OneToOne
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



    public MovieSource(int id, Source source, int movieID, String type, String region, String webURL, Format format, double price, int seasons, int episodes) {
        this.id = id;
        this.source = source;
        this.movieID = movieID;
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
