package com.cybertek.repository;

import com.cybertek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName (String userName);

    @Transactional // needed when deleting entry from DB
    void deleteByUserName(String userName);

    List<User> findAllByRoleDescriptionIgnoreCase(String role);
}
