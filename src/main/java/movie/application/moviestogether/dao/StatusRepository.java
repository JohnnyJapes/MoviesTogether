package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.application.moviestogether.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    

}
