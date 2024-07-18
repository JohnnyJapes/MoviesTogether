package movie.application.moviestogether.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import movie.application.moviestogether.entity.ListItem;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.dao.ListItemRepository;
import movie.application.moviestogether.service.MovieService;
import movie.application.moviestogether.service.UserService;
import movie.application.moviestogether.service.WatchListService;
import movie.application.moviestogether.validation.WatchListValidation;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/list")
public class WatchlistController {


    MovieService movieService;
    UserService userService;
    WatchListService watchListService;
    ListItemRepository itemRepo;




    public WatchlistController(MovieService movieService, UserService userService, WatchListService watchListService, ListItemRepository itemRepo) {
        this.movieService = movieService;
        this.userService = userService;
        this.watchListService = watchListService;
        this.itemRepo = itemRepo;
    }



    //Create
    @PostMapping("/create")
    public String createWatchList(@Valid @ModelAttribute("newWatchList") WatchListValidation listValidation
            ,BindingResult bindingResult,
            Model theModel, Authentication authentication) 
        {

        // form validation
		 if (bindingResult.hasErrors()){
			 return "Invalid Input";
		 }

        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        WatchList watchList = new WatchList();
        watchList.setUserID(theUser.getId());
        watchList.setName(listValidation.getName());
        watchListService.save(watchList);

        
        return "Successfully created list: " + listValidation.getName();
    }


    //Read
    @GetMapping("/")
    public String getMethodName(@RequestParam(name = "listId",required = true) Integer listId, 
    Model theModel, Authentication authentication) {
        return new String();
    }


    //Update
    @PostMapping("/addListItem")
    public String addListItem(@RequestParam(name = "tmdbId",required = true) Integer tmbdId, @RequestParam(name = "listId",required = true) Integer listId, 
    Model theModel, Authentication authentication) {
        //TODO: better return
        
        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        Movie addition = movieService.findByTmdb_id(tmbdId);


        if(addition.getDirector() == null) movieService.addDirector(addition);
        if(addition.getPosterPath() == null) movieService.getDetails(addition);
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

        //Delete
        //test info = localhost:8080/api/list/removeListItem?itemId=4&listId=1
        @PostMapping("/removeListItem")
        public String removeListItem(@RequestParam(name = "itemId",required = true) Integer itemId, @RequestParam(name = "listId",required = true) Integer listId, 
        Model theModel, Authentication authentication) {


            WatchList list = watchListService.findById(listId);
            String userName = authentication.getName();
            User theUser = userService.findByUserName(userName);


            if(list.getUserID() != theUser.getId()){
                throw new ResponseStatusException(HttpStatusCode.valueOf(400));
            }

            ListItem item = new ListItem();
            item.setRank(-1);
            List<ListItem> items = list.getMovies();
            int listSize = items.size();
            for (int i = 0; i< listSize; i++){
                if (items.get(i).getId() == itemId) item = items.get(i);
            }
            if(item.getRank() == -1) throw new ResponseStatusException(HttpStatusCode.valueOf(400));
            int oldRank = item.getRank();
            
            System.out.println("TEST");
            //remove from list so item can be deleted from database
            list.getMovies().remove(oldRank-1);
            itemRepo.delete(item);
            //fix ranks
            for (int i = oldRank-1; i < items.size(); i++ ){
                int currentRank = items.get(i).getRank();
                items.get(i).setRank(currentRank -1);
            }

            watchListService.save(list);


            return "Success";
        }
    
    
    
}
