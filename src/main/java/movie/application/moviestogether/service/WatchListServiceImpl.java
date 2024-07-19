package movie.application.moviestogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import movie.application.moviestogether.dao.WatchlistRepository;
import movie.application.moviestogether.entity.ListItem;
import movie.application.moviestogether.entity.Movie;
import movie.application.moviestogether.entity.WatchList;

@Service
public class WatchListServiceImpl implements WatchListService {

    private WatchlistRepository watchRepo;
    private MovieService movieService;


    @Autowired
    public WatchListServiceImpl(WatchlistRepository watchRepo, MovieService movieService) {
        this.watchRepo = watchRepo;
        this.movieService = movieService;
    }


    @Override
    @Transactional
    public void save(WatchList list) {
        watchRepo.save(list);
    }

    @Override
    public void addMovie(WatchList list, Movie movie) {

        ListItem newItem = new ListItem();

        newItem.setListID(list.getId());

        int listSize = list.getMovies().size();

        ListItem lastItem = list.getMovies().get(listSize -1);
    }

    @Override
    public WatchList findById(int id) {
        WatchList result = watchRepo.findById(id);
		if (result != null) return  result;
		else
			throw new RuntimeException("Did not find Watch List with ID - " +id);
    }

    @Override
    public List<WatchList> findByUserID(int userID) {
        return watchRepo.findByUserID(userID);
    }


    @Override
    public void delete(WatchList list) {
        watchRepo.delete(list);
    }
    
}
