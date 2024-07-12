package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.application.moviestogether.entity.Movie;
import java.util.List;



public interface MovieRepository  extends JpaRepository<Movie,Integer> {
 
    
    Movie findById(int id);

    Movie findByTmdbID(int tmdb_id);

    List<Movie> findByTitle(String title);
}
