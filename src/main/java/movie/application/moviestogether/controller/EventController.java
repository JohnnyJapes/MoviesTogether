package movie.application.moviestogether.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import movie.application.moviestogether.dao.ListItemRepository;
import movie.application.moviestogether.entity.Event;
import movie.application.moviestogether.entity.ListItem;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.model.EventBase;
import movie.application.moviestogether.service.EventService;
import movie.application.moviestogether.service.WatchListService;
import movie.application.moviestogether.validation.WatchListValidation;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/event")
public class EventController {

    private EventService eventService;
    private WatchListService watchListService;



    @Autowired
    public EventController(EventService eventService, WatchListService watchListService) {
        this.eventService = eventService;
        this.watchListService = watchListService;

    }



    @PostMapping("/create")
    public String createEventForm(@ModelAttribute("event") EventBase data, Model model, Authentication authentication) {
        
        
        System.out.println(data);
        WatchList watchList = watchListService.findById(data.getListId());
        List<ListItem> listItems =  watchList.getMovies();
        ListItem selectedItem = listItems.get(data.getRank() - 1);

        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("watchList", watchList);

        Event newEvent = new Event();

        newEvent.setEvent_datetime(data.getDate());

        newEvent.setMovie(selectedItem.getMovie());

        model.addAttribute("event", newEvent);


        return "events/eventForm";
    }
    


    

}
