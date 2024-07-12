package movie.application.moviestogether.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.WatchList;

import java.util.List;



public interface WatchlistRepository  extends JpaRepository<WatchList,Integer> {


    WatchList findById(int id);

    List<WatchList> findByUserID(int userID);

    
}
