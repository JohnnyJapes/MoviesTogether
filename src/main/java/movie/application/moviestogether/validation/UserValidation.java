package movie.application.moviestogether.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserValidation {



	private String firstName;
	

	private String lastName;

    @NotNull(message = "is required")
	@Size(min = 5, message = "must be at least 5 characters")
	private String userName;
	
	@NotNull(message = "is required")
	@Size(min = 5, message = "must be at least 5 characters")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min = 3, message = "must be at least 3 characters")
	private String password;

	public UserValidation() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	};

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
