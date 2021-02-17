package com.cybertek.repository;

import com.cybertek.entity.AccountDetails;
import com.cybertek.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    // TODO: Write a derived query to list all accounts with a specific country or state
    List<AccountDetails> findAllByCountryOrState(String country, String state);

    // TODO: Write a derived query to list all accounts with age lower than or equal to a specific value
    List<AccountDetails> findAllByAgeLessThanEqual(Integer age);

    // TODO: Write a derived query to list all accounts with a specific role
    List<AccountDetails> findAllByRole(Role role);

    // TODO: Write a derived query to list all accounts between a range of ages
    List<AccountDetails> findAllByAgeBetween(Integer age1, Integer age2);

    // TODO: Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<AccountDetails> findAllByAddressStartingWith(String pattern);

    // TODO: Write a derived query to sort the list of accounts with age
    List<AccountDetails> findByOrderByAgeDesc();

    // ------------------- JPQL QUERIES ------------------- //

    // TODO: Write a JPQL query that returns all accounts
    @Query("SELECT a FROM AccountDetails a")
    List<AccountDetails> retrieveAllAccountsJPQL();

    // TODO: Write a JPQL query to list all admin accounts
    @Query("SELECT a FROM AccountDetails a WHERE a.role = 'ADMIN'")
    List<AccountDetails> retrieveAdminAccountsJPQL();

    // TODO: Write a JPQL query to sort all accounts with age
    @Query("SELECT a FROM AccountDetails a ORDER BY a.age")
    List<AccountDetails> retrieveAccountsSortedByAgeJPQL();

    // ------------------- Native QUERIES ------------------- //

    // TODO: Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "SELECT * FROM account_details WHERE age < :age", nativeQuery = true)
    List<AccountDetails> retrieveAccountsYoungerThanNative(Integer age);

    // TODO: Write a native query to read all accounts that a specific value can be containable in the name, address, country, state city
    @Query(value = "SELECT * FROM account_details WHERE name ILIKE concat('%', ?1, '%') OR address ILIKE concat('%', ?1, '%') OR country ILIKE concat('%', ?1, '%') OR state ILIKE concat('%', ?1, '%') OR city ILIKE concat('%', ?1, '%')", nativeQuery = true)
    List<AccountDetails> retrieveAccountsContainingAddressNative(String pattern);
    
}
