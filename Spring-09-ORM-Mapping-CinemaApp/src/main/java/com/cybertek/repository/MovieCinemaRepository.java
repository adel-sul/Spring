package com.cybertek.repository;

import com.cybertek.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    // TODO: Write a derived query to read movie cinema with id
    MovieCinema findByMovieCinemaId(Long id);

    // TODO: Write a derived query to count all movie cinemas with a specific cinema id
    int countAllByCinemaCinemaId(Long cinemaId);

    // TODO: Write a derived query to count all movie cinemas with a specific movie id
    int countAllByMovie_MovieId(Long movieId);

    // TODO: Write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema> findAllByDateTimeAfter(LocalDateTime dateTime);

    // TODO: Write a derived query to find the top 3 expensive movies
    List<MovieCinema> findTop3ByOrderByMoviePriceDesc();

    // TODO: Write a derived query to list all movie cinemas that contain a specific movie name
    List<MovieCinema> findAllByMovieNameContaining(String pattern);

    // TODO: Write a derived query to list all movie cinemas in a specific location
    List<MovieCinema> findAllByCinema_Location_Name(String name);

    // ------------------- JPQL QUERIES ------------------- //

    // TODO: Write a JPQL query to list all movie cinemas with after a specific date
    @Query("SELECT mc FROM MovieCinema mc WHERE mc.dateTime > ?1")
    List<MovieCinema> retrieveAllAfterADateJPQL(LocalDateTime dateTime);

    // ------------------- Native QUERIES ------------------- //

    // TODO: Write a native query to count all movie cinemas by cinema id
    @Query(value = "SELECT count(*) FROM movie_cinema WHERE cinema_id = ?1", nativeQuery = true)
    int retrieveCountByCinemaIdNative(Long cinemaId);

    // TODO: Write a native query that returns all movie cinemas by location name
    @Query(value = "SELECT * FROM movie_cinema mc JOIN cinema c ON mc.cinema_id = c.id JOIN location l ON c.location_id = l.id WHERE l.name = :locationName", nativeQuery = true)
    List<MovieCinema> retrieveAllByLocationNameNative(String locationName);

}
