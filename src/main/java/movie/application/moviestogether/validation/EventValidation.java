package movie.application.moviestogether.validation;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import movie.application.moviestogether.entity.Movie;

public class EventValidation {

    @NotNull(message = "Please Select a Movie")
    private int movieId;

    private int watchListId;
	

    @NotNull(message = "Title is required")
	@Size(min = 5, message = "must be at least 5 characters")
	private String title;

    @NotNull(message = "Date and time is required")
    private String event_datetime;


    @NotNull(message = "Location is required")
    private String location;

    private String description;

    public EventValidation() {
    }


    public EventValidation(int movieId, String title, String event_datetime, String location, String description) {
        this.movieId = movieId;
        this.title = title;
        this.event_datetime = event_datetime;
        this.location = location;
        this.description = description;
    }

    public int getMovieId() {
        return this.movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent_datetime() {
        return this.event_datetime;
    }

    public void setEvent_datetime(String event_datetime) {
        this.event_datetime = event_datetime;
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

    public int getWatchListId() {
        return this.watchListId;
    }

    public void setWatchListId(int watchListId) {
        this.watchListId = watchListId;
    }
    

}
