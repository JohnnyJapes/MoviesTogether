package movie.application.moviestogether.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import movie.application.moviestogether.entity.ListItem;




public interface ListItemRepository  extends JpaRepository<ListItem,Integer> {

    @Transactional
    public void delete(ListItem item);
    
}
