package movie.application.moviestogether.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.service.UserService;



@Controller
@RequestMapping("/")
public class HomeController {
    

    private UserService userService;

    public HomeController(){};

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/lists")
    public String getListPage(Authentication authentication, Model theModel) {

        String username = authentication.getName();
		System.out.println("username: " +username);
		User user = userService.findByUserName(username);
		//Customer customer.
		
		theModel.addAttribute("name", user.getFirstName());
		List<WatchList> lists = user.getWatchLists();
		
		theModel.addAttribute("watchLists", lists);
		//theModel.addAttribute("orders", orders);
        return "lists";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/invites")
    public String getInvitePage() {
        return "invites";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }
    
}
