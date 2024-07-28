package movie.application.moviestogether.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class tmdbControllerTest {

    private tmdbController tmdb;


    public tmdbControllerTest(tmdbController tmdb) {
        this.tmdb = tmdb;
    }
    

    @Test
    void testGetMovieWatchProviders() {
        tmdb.getMovieWatchProviders(680);

    }

    @Test
    void testGetSearchResults() {
       

    }
}
