package movie.application.moviestogether.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import movie.application.moviestogether.dao.EventJoinUserRepository;
import movie.application.moviestogether.dao.EventRepository;
import movie.application.moviestogether.dao.StatusRepository;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.Status;
import movie.application.moviestogether.model.EventJoinHelper;

@Service
public class EventJoinUserServiceImpl  implements EventJoinUserService{

    EventJoinUserRepository eventJoinUserRepository;
    StatusRepository statusRepository;
    EventRepository eventRepository;



    public EventJoinUserServiceImpl(EventJoinUserRepository eventJoinUserRepository, StatusRepository statusRepository, EventRepository eventRepository) {
        this.eventJoinUserRepository = eventJoinUserRepository;
        this.statusRepository = statusRepository;
        this.eventRepository = eventRepository;
    }




    @Override
    public void save(EventJoinUser eventJoinUser) {
        eventJoinUserRepository.save(eventJoinUser);
        return;
    }

    @Override
    public void setAttending(EventJoinUser eventJoinUser) {
        Optional<Status> result = statusRepository.findById(2);
        Status status = result.get();
        eventJoinUser.setStatus(status);
        save(eventJoinUser);
    }

    @Override
    public void setInvited(EventJoinUser eventJoinUser) {
        Optional<Status> result = statusRepository.findById(1);
        Status status = result.get();
        eventJoinUser.setStatus(status);
        save(eventJoinUser);
    }

    @Override
    public void setDeclined(EventJoinUser eventJoinUser) {
        Optional<Status> result = statusRepository.findById(3);
        Status status = result.get();
        eventJoinUser.setStatus(status);
        save(eventJoinUser);

    }

    @Override
    public void sortEvents(List<EventJoinUser> eventJoins){

        Collections.sort(eventJoins, new EventJoinHelper());
        
    }

}
