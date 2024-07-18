package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.application.moviestogether.entity.Event;

public interface EventRepository extends JpaRepository<Event,Integer> {
    
}
