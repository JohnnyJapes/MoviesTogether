package movie.application.moviestogether.service;

import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.User;

public interface EventService {

    void save(Event event);
    void delete(Event event);
    void inviteUser(Event event, User user);
    void inviteUser(int eventId, int userId);
    Event findById(int id);
    

}
