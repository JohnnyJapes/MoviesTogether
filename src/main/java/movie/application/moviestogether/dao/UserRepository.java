package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import movie.application.moviestogether.entity.User;
import java.util.List;


public interface UserRepository  extends JpaRepository<User,Integer> {
 
    
    User findByEmail(String email);
    User findByUserName(String userName);
}
