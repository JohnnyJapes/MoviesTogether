package movie.application.moviestogether;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import movie.application.moviestogether.dao.MovieRepository;
import movie.application.moviestogether.entity.Movie;

@SpringBootTest
class MoviesTogetherApplicationTests {


	@Autowired
	private MovieRepository movieRepo;

	@Test
	void contextLoads() {
	}

	@Test
    void testFindbyTitle() throws Exception {

		System.out.println("TEST");

        List<Movie> movies = movieRepo.findByTitle("Scream");

		for (int i = 0; i < movies.size(); i++) {

			System.out.println(movies.get(i).getTitle());
			
		}

    }



}
