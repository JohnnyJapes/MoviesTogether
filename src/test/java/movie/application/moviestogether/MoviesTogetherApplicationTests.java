package movie.application.moviestogether;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import movie.application.moviestogether.apiModels.TMDBsearchResults;
import movie.application.moviestogether.dao.MovieRepository;
import movie.application.moviestogether.entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

	@Test
	void testTMDBAPI() throws Exception{

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://api.themoviedb.org/3/search/movie?query=scream&include_adult=false&language=en-US&page=1")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODgxODIwZTI3OWFkZGMzN2MzYzNjOTUyYjJlM2VkNCIsIm5iZiI6MTcyMDI4MTY1NS4zNDY3NSwic3ViIjoiNjRmYjZjNjVmZmM5ZGUwZWUzYzM5MDkxIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.nSYJdYj2OYcVMf4bIIcyZ0BkOF04bYTTmiL78WdMgKI")
		  .build();
		
		Response response = client.newCall(request).execute();

		String json = response.body().string();
		ObjectMapper objectMapper = new ObjectMapper();

		TMDBsearchResults results = objectMapper.readValue(json, TMDBsearchResults.class);

		System.out.println(results.getTotal_pages());
	}



}
