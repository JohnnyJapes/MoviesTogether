package movie.application.moviestogether.service;

import java.util.List;

import movie.application.moviestogether.entity.Movie;

public interface MovieService {

    public void save(Movie movie);
    List<Movie> findByTitle(String title);
    Movie findById(int id);
    Movie findByTmdb_id(int tmdb_id);
    public void addDirector(Movie movie);
    public String getDirector(Movie movie);
    
}
