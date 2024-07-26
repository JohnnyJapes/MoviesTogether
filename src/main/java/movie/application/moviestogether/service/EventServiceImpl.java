package movie.application.moviestogether.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import movie.application.moviestogether.dao.EventRepository;
import movie.application.moviestogether.dao.StatusRepository;
import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.Status;
import movie.application.moviestogether.entity.User;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepo;
    private StatusRepository statusRepo;



    public EventServiceImpl(EventRepository eventRepo, StatusRepository statusRepo) {
        this.eventRepo = eventRepo;
        this.statusRepo = statusRepo;
    }



    @Override
    public void save(Event event) {
        eventRepo.save(event);
    }


    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }



    @Override
    public void inviteUser(Event event, User user) {
        // code cleanup
        Optional<Status> result = statusRepo.findById(1);
        Status invited = result.get();
        EventJoinUser eventJoin = new EventJoinUser(event, user,  invited);
        if (event.getInvitedUsers() == null){
            List<EventJoinUser> test = new ArrayList<EventJoinUser>();
            test.add(eventJoin);
            event.setInvitedUsers(test);
        }
        else{
            event.getInvitedUsers().add(eventJoin);
        }

        //System.out.println("users size: " + event.getInvitedUsers().size());
        //save event to database
        
        save(event);
        return;
        //throw new UnsupportedOperationException("Unimplemented method 'inviteUser'");
    }
}
