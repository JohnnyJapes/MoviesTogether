package movie.application.moviestogether.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.validation.UserValidation;
public interface UserService extends UserDetailsService {


    User findByEmail(String email);
    void save(UserValidation data);
    User findByUserName(String userName);

    void update(User user);
    
}
