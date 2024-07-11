package movie.application.moviestogether.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import movie.application.moviestogether.entity.User;
import movie.application.moviestogether.service.UserService;
import movie.application.moviestogether.validation.UserValidation;


@Controller
@RequestMapping("/register")
public class RegistrationController {

	private Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("userCheck", new UserValidation());
		
		return "register/registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("userCheck") UserValidation data,
			BindingResult theBindingResult,
			HttpSession session, Model theModel) {

		String email = data.getEmail();
		logger.info("Processing registration form for: " + email);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "register/registration-form";
		 }

		// check the database if user already exists
        User existing = userService.findByEmail(email);
        if (existing != null){
        	theModel.addAttribute("userCheck", new UserValidation());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "register/registration-form";
        }
        
        
        // create user account and store in the databse
        userService.save(data);
        
        logger.info("Successfully created user: " + email);

		// place user in the web http session for later use
		session.setAttribute("user", data);

        return "register/registration-confirmation";
	}
}
