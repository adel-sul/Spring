package com.cybertek.service;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.User;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> listAllProjects();

    ProjectDTO findByProjectCode(String projectCode);

    void save(ProjectDTO projectDTO);

    void update(ProjectDTO projectDTO);

    void delete(String projectCode);

    void complete(String projectCode);

    List<ProjectDTO> getProjectsByManager(User user);

}
