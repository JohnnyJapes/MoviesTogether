package movie.application.moviestogether.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/list")
public class WatchlistController {



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
    public String addListItem(@RequestParam(name = "tmdbId",required = true) Integer manuId, @RequestParam(required = false) List<String> filters,
    Model theModel, Authentication authentication, @RequestParam(name = "searchText",required = false) String searchString) {
        //TODO: process POST request
        
        return "BOO";
    }
    
    
    
}
