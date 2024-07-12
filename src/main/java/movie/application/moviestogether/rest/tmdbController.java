package movie.application.moviestogether.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import movie.application.moviestogether.apiModels.TMDBsearchResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/tmdb")
public class tmdbController {
    
    private Properties props = new Properties();
    

    
    @GetMapping("/search/{movieTitle}")
    public TMDBsearchResults getSearchResults(@PathVariable String movieTitle) {


        TMDBsearchResults list = new TMDBsearchResults();

		try {
            props.load(new FileInputStream("apikeys.env"));


        OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://api.themoviedb.org/3/search/movie?query="+ movieTitle +"&include_adult=false&language=en-US&page=1")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer "+props.getProperty("TMDBAPI"))
		  .build();
		
		Response response = client.newCall(request).execute();

		String json = response.body().string();
		ObjectMapper objectMapper = new ObjectMapper();

		TMDBsearchResults results = objectMapper.readValue(json, TMDBsearchResults.class);

		System.out.println(results.getTotal_pages());
        list = results;
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return list;
        
    }


        

    
}
