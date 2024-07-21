package movie.application.moviestogether.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.service.UserService;
import movie.application.moviestogether.validation.UpdateUserValidation;
import movie.application.moviestogether.validation.UserValidation;
import movie.application.moviestogether.validation.WatchListValidation;



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

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/invites")
    public String getInvitePage() {
        return "invites";
    }

    @GetMapping("/user")
    public String getUserPage(Authentication authentication, Model theModel) {

        String username = authentication.getName();
		System.out.println("username: " +username);
		User user = userService.findByUserName(username);

        theModel.addAttribute("user", user);



        return "user";
    }
    @PostMapping("/user/update")
    public String updateUser(@Valid @ModelAttribute("user") UpdateUserValidation data,
    BindingResult theBindingResult,Authentication authentication, Model theModel) {

        String username = authentication.getName();
		System.out.println("username: " +username);
		User user = userService.findByUserName(username);


        // form validation
		 if (theBindingResult.hasErrors()){
            System.out.println("Errors: "+theBindingResult.getErrorCount());
            System.out.println(theBindingResult.getAllErrors());
            return "user";
        }


        User existing = userService.findByUserName(data.getUserName());
        if (existing != null){
        	theModel.addAttribute("userCheck", new UserValidation());
			theModel.addAttribute("registrationError", "User name already exists.");

			//logger.warning("User name already exists.");
        	return "user";
        }
        user.setUserName(data.getUserName());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        userService.update(user);

        theModel.addAttribute("user", user);

        

        return "redirect:/logout";
    }
    
}
