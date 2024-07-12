package movie.application.moviestogether.service;

import java.util.List;

import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.WatchList;

public interface WatchListService {

    /**
     * Saves list input to database
     * @param list
     */
    void save(WatchList list);

    void addMovie(WatchList list, Movie movie);

    WatchList findById(int id);

    List<WatchList> findByUserID(int userID);

    
}
