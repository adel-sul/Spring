package com.cybertek.repository;

import com.cybertek.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    //    ---------------------------------DERIVED QUERIES------------------------

    // TODO: Write a derived query to read a user with an email?
    UserAccount findByEmail(String email);

    // TODO: Write a derived query to read a user with an username?
    UserAccount findByUsername(String userName);

    // TODO: Write a derived query to list all users that contain a specific name?
    List<UserAccount> findAllByAccountDetailsNameContaining(String patter);

    // TODO: Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<UserAccount> findAllByAccountDetailsNameContainingIgnoreCase(String patter);

    // TODO: Write a derived query to list all users with an age greater than a specified age?
    List<UserAccount> findAllByAccountDetailsAgeGreaterThan(int age);

    //    ----------------------------------- JPQL QUERIES--------------------------

    // TODO: Write a JPQL query that returns a user read by email?
    @Query("SELECT u FROM UserAccount u WHERE u.email = ?1")
    UserAccount retrieveUserByEmailJPQL(String email);

    // TODO: Write a JPQL query that returns a user read by username?
    @Query("SELECT u FROM UserAccount u WHERE u.username = ?1")
    UserAccount retrieveUserByUsernameJPQL(String userName);

    // TODO: Write a JPQL query that returns all users?
    @Query("SELECT u FROM UserAccount u")
    List<UserAccount> retrieveAllJPQL();

    //    ------------------------------NATIVE QUERIES------------------------

    // TODO: Write a native query that returns all users that contain a specific name?
    @Query(value = "SELECT * FROM user_account U JOIN account_details A ON U.account_details_id = A.id WHERE A.name ILIKE concat('%', ?1, '%')", nativeQuery = true)
    List<UserAccount> retrieveAllByNameContainingNative(String patter);

    // TODO: Write a native query that returns all users?
    @Query(value = "SELECT * FROM user_account", nativeQuery = true)
    List<UserAccount> retrieveAllNative();

    // TODO: Write a native query that returns all users in the range of ages?
    @Query(value = "SELECT * FROM user_account U JOIN account_details A ON U.account_details_id = A.id WHERE A.age BETWEEN ?1 AND ?2", nativeQuery = true)
    List<UserAccount> retrieveAllBetweenAgesNative(int age1, int age2);

    // TODO: Write a native query to read a user by email?
    @Query(value = "SELECT * FROM user_account WHERE email = :email", nativeQuery = true)
    UserAccount retrievedByEmailNative(String email);

}
