package movie.application.moviestogether.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import movie.application.moviestogether.dao.EventRepository;
import movie.application.moviestogether.dao.StatusRepository;
import movie.application.moviestogether.dao.UserRepository;
import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.Status;
import movie.application.moviestogether.entity.User;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepo;
    private StatusRepository statusRepo;
    private UserRepository userRepo;




    public EventServiceImpl(EventRepository eventRepo, StatusRepository statusRepo, UserRepository userRepo) {
        this.eventRepo = eventRepo;
        this.statusRepo = statusRepo;
        this.userRepo = userRepo;
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

    @Override
    public void inviteUser(int eventId, int userId) {
        // code cleanup
        Optional<Status> result = statusRepo.findById(1);
        Status invited = result.get();
        Event event = eventRepo.findById(eventId);
        Optional<User> userResult = userRepo.findById(userId);
        User user = userResult.get();
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



    @Override
    public Event findById(int id) {
        
        Event result = eventRepo.findById(id);
		if (result != null) return  result;
		else
			throw new RuntimeException("Did not find Event with ID - " +id);
	
    }
    
}
