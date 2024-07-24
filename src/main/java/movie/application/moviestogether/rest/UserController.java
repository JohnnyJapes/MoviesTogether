package movie.application.moviestogether.rest;

import org.springframework.web.bind.annotation.RestController;

import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/user")

public class UserController {


    private UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }


    //route to check that a user exists when creating invites
    @GetMapping("/check/{userName}")
    public String checkUserExists(@RequestParam String userName) {

        User user = userService.findByUserName(userName);
        if(user != null){
            return "True";
        }
        else return "False";
    }
    

}
