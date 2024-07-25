package movie.application.moviestogether.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import movie.application.moviestogether.dao.ListItemRepository;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.model.Alert;
import movie.application.moviestogether.service.MovieService;
import movie.application.moviestogether.service.UserService;
import movie.application.moviestogether.service.WatchListService;
import movie.application.moviestogether.validation.WatchListValidation;


@Controller
@RequestMapping("/lists")
public class ListController {



    MovieService movieService;
    UserService userService;
    WatchListService watchListService;
    ListItemRepository itemRepo;



    public ListController(MovieService movieService, UserService userService, WatchListService watchListService, ListItemRepository itemRepo) {
        this.movieService = movieService;
        this.userService = userService;
        this.watchListService = watchListService;
        this.itemRepo = itemRepo;
    }


    @GetMapping("")
    public String getListPage(Authentication authentication, Model theModel, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
		System.out.println("username: " +username);
		User user = userService.findByUserName(username);
		//Customer customer.
		
		theModel.addAttribute("name", user.getFirstName());
		List<WatchList> lists = user.getWatchLists();
        if (lists.size() <=0){

        }

        if (redirectAttributes.containsAttribute("alert")){
            theModel.addAttribute("alert", redirectAttributes.getAttribute("alert"));
        }
		
		theModel.addAttribute("watchLists", lists);
        theModel.addAttribute("newWatchList", new WatchListValidation());
		//theModel.addAttribute("orders", orders);
        return "lists";
    }
        //Create
    @PostMapping("/create")
    public String createWatchList(@Valid @ModelAttribute("newWatchList") WatchListValidation listValidation
            ,BindingResult bindingResult,
            Model theModel, Authentication authentication, RedirectAttributes redirectAttributes) 
        {

        // form validation
		 if (bindingResult.hasErrors()){
            theModel.addAttribute("alert", new Alert("Failed to Create new List: " + listValidation.getName()));
			 return "Invalid Input";
		 }

        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        WatchList watchList = new WatchList();
        watchList.setUserID(theUser.getId());
        watchList.setName(listValidation.getName());
        watchListService.save(watchList);

        redirectAttributes.addFlashAttribute("alert", new Alert("Created new List: " + listValidation.getName()));

        theModel.addAttribute("alert", new Alert("Created new List: " + listValidation.getName()));

        
        return "redirect:/lists";
    }
    
}
