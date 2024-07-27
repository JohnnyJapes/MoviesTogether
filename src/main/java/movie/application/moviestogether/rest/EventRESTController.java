package movie.application.moviestogether.rest;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventRESTController {

    private EventService eventService;


    public EventRESTController(EventService eventService) {
        this.eventService = eventService;
    }


    //return event
    @GetMapping("/invited/{eventId}")
    public List<EventJoinUser> getInvitedUsers(@PathVariable int eventId) {
        System.out.println("invited Users");

        Event event = eventService.findById(eventId);
        if(event != null){
            List<EventJoinUser> events = event.getInvitedUsers();
            return events;
        }
        else throw new ResponseStatusException( HttpStatusCode.valueOf(404));
    }

}
