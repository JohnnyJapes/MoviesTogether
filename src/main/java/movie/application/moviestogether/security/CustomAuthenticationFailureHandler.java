package movie.application.moviestogether.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.service.UserService;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private UserService userService;

    public CustomAuthenticationFailureHandler(UserService theUserService) {
        userService = theUserService;
    }


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
        System.out.println("In customAuthenticationFailureHandler");
        System.out.println(exception.getMessage());

//        String userName = authentication.getName();
//
//        System.out.println("userName=" + userName);
//
//        User theUser = userService.findByEmail(userName);
//
//        // now place in the session
//        HttpSession session = request.getSession();
//        session.setAttribute("user", theUser);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/");
		
	}
}
