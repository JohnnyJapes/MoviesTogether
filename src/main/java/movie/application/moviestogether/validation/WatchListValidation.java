package movie.application.moviestogether.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WatchListValidation {

    @NotNull(message="is Required")
    @Size(min=4, message = "must be at least 4 characters")
    private String name;

    public WatchListValidation() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
