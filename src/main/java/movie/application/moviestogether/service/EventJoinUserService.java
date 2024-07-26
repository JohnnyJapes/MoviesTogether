package movie.application.moviestogether.service;

import movie.application.moviestogether.entity.EventJoinUser;

public interface EventJoinUserService {

    void save(EventJoinUser eventJoinUser);
    void setAttending(EventJoinUser eventJoinUser);
    void setInvited(EventJoinUser eventJoinUser);
    void setDeclined(EventJoinUser eventJoinUser);

}
