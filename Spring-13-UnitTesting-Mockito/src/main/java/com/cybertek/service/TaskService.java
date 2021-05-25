package com.cybertek.service;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Project;
import com.cybertek.utils.Status;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Integer id);

    List<TaskDTO> listAllTasks();

    void save(TaskDTO taskDto);

    void update(TaskDTO taskDto);

    void delete(Integer id);

    Integer getCountActive(String projectCode);

    Integer getCountCompleted(String projectCode);

    void deleteByProject(ProjectDTO project);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);
    List<TaskDTO> listAllTasksByStatus(Status status);

    List<TaskDTO> listAllTasksByManager();

    void updateTaskStatus(TaskDTO taskDTO);


}
