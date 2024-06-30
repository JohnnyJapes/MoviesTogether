package movie.application.moviestogether.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movie.application.moviestogether.service.UserService;



@Controller
@RequestMapping("/")
public class HomeController {
    

    private UserService userService;

    public HomeController(){};

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/lists")
    public String getListPage() {
        return "lists";
    }
    
    
}
