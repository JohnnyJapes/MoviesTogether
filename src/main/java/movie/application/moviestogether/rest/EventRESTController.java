package movie.application.moviestogether.rest;

import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParser;

import jakarta.servlet.http.HttpServletResponse;
import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.model.InviteUsersRequest;
import movie.application.moviestogether.service.EventJoinUserService;
import movie.application.moviestogether.service.EventService;
import movie.application.moviestogether.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/event")
public class EventRESTController {

    private EventService eventService;
    private UserService userService;
    private EventJoinUserService eventJoinUserService;



    public EventRESTController(EventService eventService, UserService userService, EventJoinUserService eventJoinUserService) {
        this.eventService = eventService;
        this.userService = userService;
        this.eventJoinUserService = eventJoinUserService;
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



    //@PostMapping("/invite")
    @RequestMapping(method = RequestMethod.POST, value = "/invite")
    public String inviteUsersToEvent(@RequestBody InviteUsersRequest invitedUsers, Authentication authentication, HttpServletResponse response) {
        //TODO: process POST request
        String username = authentication.getName();
        System.out.println("username: " +username);
        User user = userService.findByUserName(username);

        int eventId = invitedUsers.getEventId();
        List<String> newUsers = invitedUsers.getNewUsers();

        if (user.getId() != eventService.findById(eventId).getOwner().getId()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(403));
        }


        for (String name : newUsers) {
            User newUser = userService.findByUserName(name);
            eventService.inviteUser(eventId, newUser.getId());

        }
        System.out.println("Invited " + newUsers.size() + " users for event ID : " + eventId);
        //JsonParser parser =  new JsonParser();
        //System.out.println("id "+eventId);

        
        return "Successfully invited user(s)";
    }

    @PostMapping("/invite/accept/{eventId}")
    public String postMethodName(@PathVariable int eventId, Authentication authentication) {
        String username = authentication.getName();
        System.out.println("username: " +username);
        User user = userService.findByUserName(username);

        eventJoinUserService.setAttending(eventId, user.getId());

        


        
        return "Successfully accepted invite to Event: " + eventId;
    }
    
    

}
