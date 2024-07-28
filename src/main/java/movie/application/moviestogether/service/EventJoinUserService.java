package movie.application.moviestogether.service;

import java.util.List;

import movie.application.moviestogether.entity.EventJoinUser;

public interface EventJoinUserService {

    void save(EventJoinUser eventJoinUser);
    void setAttending(EventJoinUser eventJoinUser);
    void setAttending(int eventId, int userId);
    void setInvited(EventJoinUser eventJoinUser);
    void setDeclined(EventJoinUser eventJoinUser);
    void sortEvents(List<EventJoinUser> eventJoins);

}
