package movie.application.moviestogether.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserValidation {

	private String firstName;
	

	private String lastName;

    @NotNull(message = "is required")
	@Size(min = 5, message = "must be at least 5 characters")
	private String userName;

    public UpdateUserValidation() {
    }

    public UpdateUserValidation(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
