package com.cybertek.repository;

import com.cybertek.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    // TODO: Write a derived query to count how many tickets a user bought
    int countAllByUserAccountUserAccountId(Long id);

    // TODO: Write a derived query to list all tickets by specific email
    List<Ticket> findAllByUserAccountEmail(String email);

    // TODO: Write a derived query to count how many tickets are sold for a specific movie
    int countAllByMovieCinemaMovieName(String name);

    // TODO: Write a derived query to list all tickets between a range of dates
    List<Ticket> findAllByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    // ------------------- JPQL QUERIES ------------------- //

    // TODO: Write a JPQL query that returns all tickets are bought from a specific user
    @Query("SELECT t FROM Ticket t WHERE t.userAccount.userAccountId = ?1")
    List<Ticket> countAllByUserJPQL(Long id);

    // TODO: Write a derived query to list all tickets between a range of dates
    @Query("SELECT t FROM Ticket t WHERE t.dateTime BETWEEN ?1 AND ?2")
    List<Ticket> retrieveAllByDateTimeBetweenJPQL(LocalDateTime start, LocalDateTime end);

    // ------------------- Native QUERIES ------------------- //

    // TODO: Write a native query to count the number of tickets a user bought
    @Query(value = "SELECT count(*) FROM ticket WHERE user_account_id = ?1", nativeQuery = true)
    int countAllByUserNative(Long id);

    // TODO: Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(value = "SELECT count(*) FROM ticket WHERE user_account_id = :id AND date_time BETWEEN :start AND :end", nativeQuery = true)
    int countAllByUserBetweenDatesNative(Long id, LocalDateTime start, LocalDateTime end);

    // TODO: Write a native query to distinct all tickets by movie name
    @Query(value = "SELECT DISTINCT(m.name) FROM ticket JOIN movie_cinema mc ON mc.id = ticket.movie_cinema_id JOIN movie m ON mc.movie_id = m.id",nativeQuery = true)
    List<String> retrieveAllDistinctMovieNames();

    // TODO: Write a derived query to list all tickets by specific email
    @Query(value = "SELECT * FROM ticket T JOIN user_account U ON T.user_account_id = U.id WHERE U.email = ?1", nativeQuery = true)
    List<Ticket> retrieveAllByUserAccountEmailNative(String email);

    // TODO: Write a native query that returns all tickets
    @Query(value = "SELECT * FROM ticket", nativeQuery = true)
    List<Ticket> retrieveAllNative();

    // TODO: Write a native query to list all tickets where a specific value should be containable in the username or name or movie name
    @Query(value = "SELECT * FROM ticket JOIN user_account ua ON ticket.user_account_id=ua.id " +
            " JOIN account_details ad ON ad.id=ua.account_details_id " +
            " JOIN movie_cinema mc ON ticket.movie_cinema_id=mc.id " +
            " JOIN movie m ON mc.movie_id = m.id " +
            " WHERE ua.username ILIKE concat('%',?1,'%') OR ad.name ILIKE concat('%',?1,'%') OR m.name ILIKE concat('%',?1,'%')",nativeQuery = true)
    List<Ticket> retrieveAllBySearchCriteria(String searchCriteria);

}
