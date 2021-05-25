package com.cybertek.repository;

import com.cybertek.entity.Project;
import com.cybertek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findByProjectCode(String projectCode);
    List<Project> findAllByAssignedManager(User manager);

}
