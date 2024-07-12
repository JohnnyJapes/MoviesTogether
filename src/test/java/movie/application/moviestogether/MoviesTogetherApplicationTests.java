package movie.application.moviestogether;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import movie.application.moviestogether.apiModels.TMDBsearchResults;
import movie.application.moviestogether.dao.MovieRepository;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.WatchList;
import movie.application.moviestogether.service.WatchListService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SpringBootTest
class MoviesTogetherApplicationTests {



	private MovieRepository movieRepo;
	private WatchListService listService;
	private static final Logger LOGGER = LoggerFactory.getLogger(MoviesTogetherApplicationTests.class);

	@Autowired
	public MoviesTogetherApplicationTests(MovieRepository movieRepo, WatchListService listService) {
		this.movieRepo = movieRepo;
		this.listService = listService;
	}
	

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

	@Test
	void testTMDBAPI() throws Exception{

		Properties props = new Properties();
		props.load(new FileInputStream("apikeys.env"));

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://api.themoviedb.org/3/search/movie?query=scream&include_adult=false&language=en-US&page=1")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer "+ props.getProperty("TMDBAPI"))
		  .build();
		
		Response response = client.newCall(request).execute();

		String json = response.body().string();
		ObjectMapper objectMapper = new ObjectMapper();

		TMDBsearchResults results = objectMapper.readValue(json, TMDBsearchResults.class);


		assertEquals(results.getResults().get(1).getTitle(), "Scream");
	}

	@Test
	void testWatchListSort() throws Exception{

		WatchList list = listService.findById(1);
		assertEquals(list.getMovies().get(0).getRank(), 1);

	}


	@Test
	void testGetDirector() throws Exception{

		WatchList list = listService.findById(1);
		assertEquals(list.getMovies().get(0).getRank(), 1);

	}




}
