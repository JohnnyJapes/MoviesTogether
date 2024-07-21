package movie.application.moviestogether.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import movie.application.moviestogether.entity.User;

@SpringBootTest
public class UserServiceImplTest {

    private UserService userService;


    @Autowired
    public UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }

    
    @Test
    void testFindByEmail() {

        User user = userService.findByEmail("lah5544@psu.edu");

        assertEquals("lah", user.getUserName());

    }

    @Test
    void testFindByUserName() {


        User user = userService.findByUserName("lah");

        assertEquals("lah", user.getUserName());
    }

}
