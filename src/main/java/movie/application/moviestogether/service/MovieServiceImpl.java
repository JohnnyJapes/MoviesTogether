package movie.application.moviestogether.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import movie.application.moviestogether.apiModels.TMDBsearchResults;
import movie.application.moviestogether.dao.MovieRepository;
import movie.application.moviestogether.entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.text.StringEscapeUtils;

@Service
public class MovieServiceImpl implements MovieService {


    private MovieRepository movieRepo;
    private Properties props = new Properties();


    @Autowired
    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }




    @Override
    @Transactional
    public void save(Movie movie) {
        //save using repo
        movieRepo.save(movie);
    }

    @Override
    public List<Movie> findByTitle(String title) {
       
        return movieRepo.findByTitle(title);

    }

    @Override
    public Movie findById(int id) {
        Movie result = movieRepo.findById(id);
		if (result != null) return  result;
		else
			throw new RuntimeException("Did not find movie with ID - " +id);
	}
    

    @Override
    public Movie findByTmdb_id(int tmdb_id) {
        Movie result = movieRepo.findByTmdbID(tmdb_id);
		if (result != null){ return  result;}
		else
			throw new RuntimeException("Did not find movie with TMDB ID - " +tmdb_id);
    }


    public void addDirector(Movie movie){
        String directorName = getDirector(movie);
        if(directorName != "") {
            movie.setDirector(directorName);
            save(movie);
        }
        else
            throw new RuntimeException("Could not find director for provided movie with TMDB ID - " + movie.getTmdbID());
    }


    public String getDirector(Movie movie){
        OkHttpClient client = new OkHttpClient();
        String query = StringEscapeUtils.escapeHtml4(Integer.toString(movie.getTmdbID()));

        String directorName = "";
        try{

            props.load(new FileInputStream("apikeys.env"));

            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/" + query + "/credits?language=en-US")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer "+props.getProperty("TMDBAPI"))
                    .build();
            Response response = client.newCall(request).execute();
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(response.body().string());
            JsonToken token = parser.nextToken();

        while(!"crew".equals(parser.currentName())) token = parser.nextToken();
        if (token == JsonToken.FIELD_NAME && "crew".equals(parser.currentName())) {

            //System.out.println("Cast - \n");
            token = parser.nextToken();
            token = parser.nextToken();
            token = parser.nextToken();// // Read left bracket i.e. [
            // Loop to print array elements until right bracket i.e ]
            while (token != JsonToken.END_ARRAY){
                while (!"id".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                int id = parser.getIntValue();
                while (!"name".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                directorName = parser.getText();
                while (!"job".equals(parser.currentName())) token = parser.nextToken();
                token = parser.nextToken();
                if (parser.getText().equals("Director")) {
                    
                    //movie.setDirector(directorName);
                    break;
                }
            }
        }


        parser.close();

        }
        catch(Error | IOException e){
            System.out.println(e);
            e.printStackTrace();

        }
        return directorName;
    }




    @Override
    public void getDetails(Movie movie) {

        OkHttpClient client = new OkHttpClient();
        String query = StringEscapeUtils.escapeHtml4(Integer.toString(movie.getTmdbID()));

        String poster_path = "";
        String overview = "";
        try{

            props.load(new FileInputStream("apikeys.env"));

            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/" + query + "?language=en-US")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer "+props.getProperty("TMDBAPI"))
                    .build();
            Response response = client.newCall(request).execute();
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(response.body().string());
            JsonToken token = parser.nextToken();

        while(!"overview".equals(parser.currentName())) token = parser.nextToken();
        token = parser.nextToken();
        overview = parser.getText();
        while(!"popularity".equals(parser.currentName())) token = parser.nextToken();
        while(!"poster_path".equals(parser.currentName())) token = parser.nextToken();
        token = parser.nextToken();
        poster_path = parser.getText();
        parser.close();

        }
        catch(Error | IOException e){
            System.out.println(e);
            e.printStackTrace();

        }

        movie.setPosterPath(poster_path);
        movie.setDescription(overview);
        save(movie);
        // ObjectMapper mapper = new ObjectMapper();
        // // Configure ObjectMapper to ignore unknown properties
        // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        // throw new UnsupportedOperationException("Unimplemented method 'getDetails'");
    }

  
    
}
