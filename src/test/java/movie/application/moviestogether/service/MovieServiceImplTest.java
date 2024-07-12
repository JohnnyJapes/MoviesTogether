package movie.application.moviestogether.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import movie.application.moviestogether.entity.Movie;

@SpringBootTest
public class MovieServiceImplTest {



    private MovieService movieService;

    @Autowired
    public MovieServiceImplTest(MovieService movieService) {
        this.movieService = movieService;
    }


    @Test
    void testGetDirector() {

        Movie movie = movieService.findByTmdb_id(680);
        String result = movieService.getDirector(movie);

        assertEquals(result, "Quentin Tarantino");

    }
}
