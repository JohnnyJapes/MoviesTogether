package movie.application.moviestogether.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import movie.application.moviestogether.entity.ListItem;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.service.MovieService;
import movie.application.moviestogether.service.UserService;
import movie.application.moviestogether.service.WatchListService;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/list")
public class WatchlistController {


    MovieService movieService;
    UserService userService;
    WatchListService watchListService;



    //Create
    @PostMapping("/create")
    public String createWatchList(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }


    //Read
    @GetMapping("/")
    public String getMethodName(@RequestParam(name = "manuId",required = false) Integer manuId, @RequestParam(required = false) List<String> filters,
    Model theModel, Authentication authentication, @RequestParam(name = "searchText",required = false) String searchString) {
        return new String();
    }


    //Update
    @PostMapping("/addListItem")
    public String addListItem(@RequestParam(name = "tmdbId",required = true) Integer tmbdId, @RequestParam(name = "listId",required = true) Integer listId, 
    Model theModel, Authentication authentication) {
        //TODO: process POST request


        
        String userName = authentication.getName();

        System.out.println("userName=" + userName);

        User theUser = userService.findByUserName(userName);
        Movie addition = movieService.findByTmdb_id(tmbdId);


        if(addition.getDirector() == "") movieService.addDirector(addition);
        List<WatchList> userWatchlists = theUser.getWatchLists();
        WatchList targetWatchList = new WatchList();
        targetWatchList.setName("notFound");
        for (int i = 0; i < userWatchlists.size(); i++){
            if( userWatchlists.get(i).getId() == listId)
                targetWatchList = userWatchlists.get(i);

        }
        if(targetWatchList.getName() == "notFound"){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
        //setting up new list item
        ListItem newItem = new ListItem();
        newItem.setMovie(addition);
        newItem.setListID(targetWatchList.getId());
        newItem.setRank(targetWatchList.getMovies().size()+1);

        targetWatchList.getMovies().add(newItem);


        watchListService.save(targetWatchList);
        
        

        
        return "Success: Added " + addition.getTitle() +  " to list" ;
    }
    
    
    
}
