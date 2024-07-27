package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.application.moviestogether.entity.Event;
import java.util.List;


public interface EventRepository extends JpaRepository<Event,Integer> {

    Event findById(int id);;
    
}
