package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.User;

public interface EventJoinUserRepository extends JpaRepository<EventJoinUser, Integer>{

    EventJoinUser findByUserAndEvent(User user, Event event );
}
