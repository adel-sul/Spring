package com.cybertek.repository;

import com.cybertek.entity.Movie;
import com.cybertek.enums.MovieState;
import com.cybertek.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    // TODO: Write a derived query to read a movie with a name
    Movie findByName(String name);

    // TODO: Write a derived query to list all movies between a range of prices
    List<Movie> findAllByPriceBetween(BigDecimal price1, BigDecimal price2);

    // TODO: Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> findAllByDurationIn(List<Integer> durations);

    // TODO: Write a derived query to list all movies with higher than a specific release date
    List<Movie> findAllByReleaseDateAfter(LocalDate date);

    // TODO: Write a derived query to list all movies with a specific state and type
    List<Movie> findAllByStateAndType(MovieState state, MovieType type);

    // ------------------- JPQL QUERIES ------------------- //

    // TODO: Write a JPQL query to list all movies between a range of prices
    @Query("SELECT m FROM Movie m WHERE m.price BETWEEN ?1 AND ?2")
    List<Movie> retrieveAllInPriceRangeJPQL(BigDecimal price1, BigDecimal price2);


    // TODO: Write a JPQL query that returns all movie names
    @Query("SELECT m.name FROM Movie m")
    List<String> retrieveMovieNamesJPQL();

    // ------------------- Native QUERIES ------------------- //

    // TODO: Write a native query that returns a movie by name
    @Query(value = "SELECT * FROM movie WHERE name = ?1", nativeQuery = true)
    Optional<Movie> retrieveMovieByNameNative(String name);

    // TODO: Write a native query that return the list of movies in a specific range of prices
    @Query(value = "SELECT * FROM movie WHERE price BETWEEN :price1 AND :price2", nativeQuery = true)
    List<Movie> retrieveAllInPriceRangeNative(BigDecimal price1, BigDecimal price2);

    // TODO: Write a native query to return all movies where duration exists in the range of duration
    @Query(value = "SELECT * FROM movie WHERE duration IN ?1", nativeQuery = true)
    List<Movie> retrieveAllByDurationInNative(List<Integer> durations);

    // TODO: Write a native query to list the top 5 most expensive movies
    @Query(value = "SELECT * FROM movie ORDER BY price desc LIMIT 5", nativeQuery = true)
    List<Movie> retrieveTop5MostExpensive();

}
