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
