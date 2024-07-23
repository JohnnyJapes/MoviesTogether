package movie.application.moviestogether.service;

import org.springframework.stereotype.Service;

import movie.application.moviestogether.dao.EventRepository;
import movie.application.moviestogether.entity.Event;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepo;


    public EventServiceImpl(EventRepository eRepo){
        super();
        this.eventRepo = eRepo;
    }



    @Override
    public void save(Event event) {
        eventRepo.save(event);
    }


    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }
}
