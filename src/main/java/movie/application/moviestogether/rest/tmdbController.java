package movie.application.moviestogether.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import movie.application.moviestogether.apiModels.TMDBsearchResults;
import movie.application.moviestogether.apiModels.TMDBwatchProvider;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.service.EventService;
import movie.application.moviestogether.service.MovieService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/tmdb")
public class tmdbController {
    
    private Properties props = new Properties();
    private MovieService movieService;
    private EventService eventService;

    

    public tmdbController(Properties props, MovieService movieService, EventService eventService) {
        this.props = props;
        this.movieService = movieService;
        this.eventService = eventService;
    }


    
    @GetMapping(value = "/search/{movieTitle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TMDBsearchResults getSearchResults(@PathVariable String movieTitle) {

		try {
        TMDBsearchResults list = new TMDBsearchResults();
        OkHttpClient client;

            props.load(new FileInputStream("apikeys.env"));


        client = new OkHttpClient();

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

        
        return list;
        } 
        catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
            
        }
    }

    @GetMapping(value = "/watchProviders/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TMDBwatchProvider> getMovieWatchProviders(@PathVariable int movieId) {

        Movie movie = movieService.findById(movieId);

		try {
        TMDBsearchResults list = new TMDBsearchResults();
        OkHttpClient client;

            props.load(new FileInputStream("apikeys.env"));


        client = new OkHttpClient();
        System.out.println("Making TMDB request: " + "https://api.themoviedb.org/3/movie/"+ movie.getTmdbID() +"/watch/providers");

		Request request = new Request.Builder()
		  .url("https://api.themoviedb.org/3/movie/"+ movie.getTmdbID() +"/watch/providers")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer "+props.getProperty("TMDBAPI"))
		  .build();

		Response response = client.newCall(request).execute();

        JsonFactory factory = new JsonFactory();
        //System.out.println(response.body().string());
        JsonParser parser = factory.createParser(response.body().string());
        JsonToken token = parser.nextToken();

        List<TMDBwatchProvider> providers = new ArrayList<>();
        String logo_path, provider_name, type, link;
        int provider_id, display_priority;
        parser.nextToken();
        while(!"US".equals(parser.currentName()) && token != null) token = parser.nextToken();
        if (token == null){
            TMDBwatchProvider temp = new TMDBwatchProvider();
            temp.setType("EMPTY");
            providers.add(temp);
            return providers;
        }
        while(!"link".equals(parser.currentName())) token = parser.nextToken();
        token = parser.nextToken();
        link = parser.getText();
        System.out.println(parser.getText());
        while(!"rent".equals(parser.currentName())) token = parser.nextToken();
        if (token == JsonToken.FIELD_NAME && "rent".equals(parser.currentName())) {

            while (token != JsonToken.START_OBJECT)token = parser.nextToken();
            //System.out.println("Cast - \n");
            // token = parser.nextToken();
            // token = parser.nextToken();
            // token = parser.nextToken();// // Read left bracket i.e. [
            // Loop to print array elements until right bracket i.e ]
            //while(!"logo_path".equals(parser.currentName())) token = parser.nextToken();
            while (token != JsonToken.END_ARRAY){
                while (!"logo_path".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                logo_path = parser.getText();
                //System.out.println(parser.getText());
                while (!"provider_id".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_id = parser.getIntValue();
                while (!"provider_name".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_name = parser.getText();
                while (!"display_priority".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                display_priority = parser.getIntValue();
                type = "rent";
                providers.add(new TMDBwatchProvider(logo_path, provider_name, type, link, provider_id, display_priority));
                token = parser.nextToken();
                token = parser.nextToken();
            }
            token = parser.nextToken();
            type = "buy";
            System.out.println("HALFWAY");
            while (token != JsonToken.END_ARRAY){
                while (!"logo_path".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                logo_path = parser.getText();
                while (!"provider_id".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_id = parser.getIntValue();
                while (!"provider_name".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_name = parser.getText();
                while (!"display_priority".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                display_priority = parser.getIntValue();
                providers.add(new TMDBwatchProvider(logo_path, provider_name, type, link, provider_id, display_priority));
                token = parser.nextToken();
                token = parser.nextToken();
                
            }
        }


        parser.close();
        return providers;

        }
        catch(Error | IOException e){
            System.out.println(e);
            e.printStackTrace();

        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(500));

		// String json = response.body().string();
		// ObjectMapper objectMapper = new ObjectMapper();

		// TMDBsearchResults results = objectMapper.readValue(json, TMDBsearchResults.class);

		// System.out.println(results.getTotal_pages());
        // list = results;

        
        // return list;
        // } 
        // catch (IOException e) {
        //     e.printStackTrace();
        //     throw new ResponseStatusException(HttpStatusCode.valueOf(500));
            
        // }
    }
        

    @GetMapping(value = "/watchProviders/event/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TMDBwatchProvider> getMovieWatchProvidersFromEvent(@PathVariable int eventId) {

        Movie movie = eventService.findById(eventId).getMovie();

		try {
        TMDBsearchResults list = new TMDBsearchResults();
        OkHttpClient client;

            props.load(new FileInputStream("apikeys.env"));


        client = new OkHttpClient();
        System.out.println("Making TMDB request: " + "https://api.themoviedb.org/3/movie/"+ movie.getTmdbID() +"/watch/providers");

		Request request = new Request.Builder()
		  .url("https://api.themoviedb.org/3/movie/"+ movie.getTmdbID() +"/watch/providers")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer "+props.getProperty("TMDBAPI"))
		  .build();

		Response response = client.newCall(request).execute();

        JsonFactory factory = new JsonFactory();
        //System.out.println(response.body().string());
        JsonParser parser = factory.createParser(response.body().string());
        JsonToken token = parser.nextToken();

        List<TMDBwatchProvider> providers = new ArrayList<>();
        String logo_path, provider_name, type, link;
        int provider_id, display_priority;
        parser.nextToken();
        while(!"US".equals(parser.currentName()) && token != null) token = parser.nextToken();
        if (token == null){
            TMDBwatchProvider temp = new TMDBwatchProvider();
            temp.setType("EMPTY");
            providers.add(temp);
            return providers;
        }
        while(!"link".equals(parser.currentName())) token = parser.nextToken();
        token = parser.nextToken();
        link = parser.getText();
        System.out.println(parser.getText());
        while(!"rent".equals(parser.currentName())) token = parser.nextToken();
        if (token == JsonToken.FIELD_NAME && "rent".equals(parser.currentName())) {

            while (token != JsonToken.START_OBJECT)token = parser.nextToken();
            //System.out.println("Cast - \n");
            // token = parser.nextToken();
            // token = parser.nextToken();
            // token = parser.nextToken();// // Read left bracket i.e. [
            // Loop to print array elements until right bracket i.e ]
            //while(!"logo_path".equals(parser.currentName())) token = parser.nextToken();
            while (token != JsonToken.END_ARRAY){
                while (!"logo_path".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                logo_path = parser.getText();
                //System.out.println(parser.getText());
                while (!"provider_id".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_id = parser.getIntValue();
                while (!"provider_name".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_name = parser.getText();
                while (!"display_priority".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                display_priority = parser.getIntValue();
                type = "rent";
                providers.add(new TMDBwatchProvider(logo_path, provider_name, type, link, provider_id, display_priority));
                token = parser.nextToken();
                token = parser.nextToken();
            }
            token = parser.nextToken();
            type = "buy";
            System.out.println("HALFWAY");
            while (token != JsonToken.END_ARRAY){
                while (!"logo_path".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                logo_path = parser.getText();
                while (!"provider_id".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_id = parser.getIntValue();
                while (!"provider_name".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                provider_name = parser.getText();
                while (!"display_priority".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                display_priority = parser.getIntValue();
                providers.add(new TMDBwatchProvider(logo_path, provider_name, type, link, provider_id, display_priority));
                token = parser.nextToken();
                token = parser.nextToken();
                
            }
        }


        parser.close();
        System.out.println("DONE");
        return providers;

        }
        catch(Error | IOException e){
            System.out.println(e);
            e.printStackTrace();

        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(500));

		// String json = response.body().string();
		// ObjectMapper objectMapper = new ObjectMapper();

		// TMDBsearchResults results = objectMapper.readValue(json, TMDBsearchResults.class);

		// System.out.println(results.getTotal_pages());
        // list = results;

        
        // return list;
        // } 
        // catch (IOException e) {
        //     e.printStackTrace();
        //     throw new ResponseStatusException(HttpStatusCode.valueOf(500));
            
        // }
    }
        

    
}
