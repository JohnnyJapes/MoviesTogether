package movie.application.moviestogether.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import movie.application.moviestogether.dao.EventJoinUserRepository;
import movie.application.moviestogether.dao.ListItemRepository;
import movie.application.moviestogether.dao.StatusRepository;
import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.EventJoinUser;
import movie.application.moviestogether.entity.ListItem;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.Status;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.model.Alert;
import movie.application.moviestogether.model.EventBase;
import movie.application.moviestogether.service.EventJoinUserService;
import movie.application.moviestogether.service.EventService;
import movie.application.moviestogether.service.MovieService;
import movie.application.moviestogether.service.UserService;
import movie.application.moviestogether.service.WatchListService;
import movie.application.moviestogether.validation.EventValidation;
import movie.application.moviestogether.validation.WatchListValidation;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/event")
public class EventController {

    private EventService eventService;
    private WatchListService watchListService;
    private MovieService movieService;
    private UserService userService;
    private EventJoinUserService eventJoinUserService;
    private StatusRepository statusRepository;



    @Autowired


    public EventController(EventService eventService, WatchListService watchListService, MovieService movieService, UserService userService, EventJoinUserService eventJoinUserService, StatusRepository statusRepository) {
        this.eventService = eventService;
        this.watchListService = watchListService;
        this.movieService = movieService;
        this.userService = userService;
        this.eventJoinUserService = eventJoinUserService;
        this.statusRepository = statusRepository;
    }





        // //create from the watchlist page
        // @GetMapping("/newEvent")
        // public String getEventForm(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
            
            
        //     EventValidation data = (EventValidation) redirectAttributes.getAttribute("eventValidation");

        //     System.out.println(data);
        //     WatchList watchList = watchListService.findById(data.getListId());
        //     List<ListItem> listItems =  watchList.getMovies();
        //     ListItem selectedItem = listItems.get(data.getRank() - 1);
    
        //     model.addAttribute("selectedItem", selectedItem);
        //     model.addAttribute("watchList", watchList);
    
        //     EventValidation newEvent = new EventValidation();
    
        //     newEvent.setEvent_datetime(data.getDate());
    
        //     newEvent.setMovieId(selectedItem.getMovie().getId());
    
        //     model.addAttribute("event", newEvent);
    
    
        //     return "events/eventForm";
        // }
    



    //create from the watchlist page
    @PostMapping("/newEvent")
    public String createEventForm(@ModelAttribute("event") EventBase data, Model model, Authentication authentication
        , RedirectAttributes redirectAttributes) {
        
        if(redirectAttributes.containsAttribute("eventValidation")){

            EventValidation tempEvent = (EventValidation) redirectAttributes.getAttribute("eventValidation");
            data.setListId(tempEvent.getWatchListId());
            data.setDate(tempEvent.getEvent_datetime());
        }
        

        System.out.println(data);
        WatchList watchList = watchListService.findById(data.getListId());
        List<ListItem> listItems =  watchList.getMovies();

        if(redirectAttributes.containsAttribute("eventValidation")){

            EventValidation tempEvent = (EventValidation) redirectAttributes.getAttribute("eventValidation");
            for (int i = 0; i < listItems.size(); i++) {
                if (listItems.get(i).getMovie().getId() == tempEvent.getMovieId()) {
                    data.setRank(listItems.get(i).getRank());
                    break;
                }
            }
        }

        ListItem selectedItem = listItems.get(data.getRank() - 1);

        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("watchList", watchList);

        EventValidation newEvent = new EventValidation();

        newEvent.setEvent_datetime(data.getDate());

        newEvent.setMovieId(selectedItem.getMovie().getId());

        model.addAttribute("event", newEvent);

        if(redirectAttributes.containsAttribute("alert")){
            model.addAttribute("alert", redirectAttributes.getAttribute("alert"));

        }


        return "events/eventForm";
    }

    //create the event
    @PostMapping("/create")
    public String createEvent(@Valid @ModelAttribute("event") EventValidation data,
     BindingResult bindingResult, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        
        
        String username = authentication.getName();
        System.out.println("username: " +username);
        User user = userService.findByUserName(username);

        // form validation
		 if (bindingResult.hasErrors()){
            System.out.println("Validation Errors");
            redirectAttributes.addAttribute("alert", new Alert("Failed to create event. "));
            redirectAttributes.addAttribute("eventValidation", data);
			return "redirect:event/newEvent";
		 }
        Event event = new Event();

        event.setLocation(data.getLocation());
        event.setDescription(data.getDescription());
        event.setEvent_datetime(data.getEvent_datetime());
        Movie movie = movieService.findById(data.getMovieId());
        event.setTitle(data.getTitle());
        event.setMovie(movie);
        event.setOwner(user);
        //eventService.save(event);

        //create link to user
        Optional<Status> result = statusRepository.findById(2);
        Status attending = result.get();
        EventJoinUser eventJoin = new EventJoinUser(event, user,  attending);
        List<EventJoinUser> test = new ArrayList<EventJoinUser>();
        test.add(eventJoin);
        event.setInvitedUsers(test);
        System.out.println("users size: " + event.getInvitedUsers().size());
        //save event to database
        
        eventService.save(event);

        //eventJoinUserRepository.save(eventJoin);
        return "redirect:/event/list";
    }


    //Webpage to list all events a user is attending
    @GetMapping("/list")
    public String getEventsPage(Model model, Authentication authentication) {
        String username = authentication.getName();
		System.out.println("username: " +username);
		User user = userService.findByUserName(username);

    
        List<EventJoinUser> userEvents = user.getEvents();
        eventJoinUserService.sortEvents(userEvents);
        List<Event> events = new ArrayList<Event>();
        for (EventJoinUser eventJoin : userEvents) {
            events.add(eventJoin.getEvent());
        }

        model.addAttribute("userJoinEvents", userEvents);
        model.addAttribute("events", events);
        model.addAttribute("currentUser", user);

        return "events/eventList";
    }

        //Webpage to list all invites
        @GetMapping("/invites")
        public String getInvitesPage(Model model, Authentication authentication) {
            String username = authentication.getName();
            System.out.println("username: " +username);
            User user = userService.findByUserName(username);
    
            List<EventJoinUser> userEvents = user.getEvents();
            List<Event> events = new ArrayList<Event>();
            for (EventJoinUser eventJoin : userEvents) {
                events.add(eventJoin.getEvent());
            }
    
            model.addAttribute("userJoinEvents", userEvents);
            model.addAttribute("events", events);
            model.addAttribute("currentUser", user);
            return "events/eventList";
        }
    
    
}
