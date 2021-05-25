package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import com.cybertek.entity.User;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.TaskService;
import com.cybertek.utils.Status;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;
    ProjectMapper projectMapper;
    UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTO findById(Integer id) {
        Task task = taskRepository.findById(id).get();
        return taskMapper.convertToDto(task);
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList()); // method reference
    }

    @Override
    public void save(TaskDTO taskDto) {
        taskDto.setTaskStatus(Status.OPEN);
        taskDto.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(taskDto));
    }

    @Override
    public void update(TaskDTO taskDto) {
        Task task = taskRepository.findById(taskDto.getId()).get();
        Task updatedTask = taskMapper.convertToEntity(taskDto);
        updatedTask.setId(task.getId());
        updatedTask.setAssignedDate(task.getAssignedDate());
        taskRepository.save(updatedTask);
    }

    @Override
    public void delete(Integer id) {
        Task task = taskRepository.findById(id).get();
        task.setDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public Integer getCountActive(String projectCode) {
        return taskRepository.totalActiveTasksByProject(projectCode);
    }

    @Override
    public Integer getCountCompleted(String projectCode) {
        return taskRepository.totalCompletedTasksByProject(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO project) {
        List<TaskDTO> taskDTOS = taskRepository.findAllByProject(projectMapper.convertToEntity(project)).stream().map(taskMapper::convertToDto).collect(Collectors.toList());
        taskDTOS.forEach(taskDTO -> delete(taskDTO.getId()));
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        User user = userRepository.findByUserName("admin@admin.com");
        List<Task> tasks = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);
        return  tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {
        User user = userRepository.findByUserName("admin@admin.com");
        List<Task> tasks = taskRepository.findAllByTaskStatusAndAssignedEmployee(status, user);
        return  tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByManager() {
        User user = userRepository.findByUserName("adel1986@yandex.ru");
        List<Task> tasks = taskRepository.findAllByProjectAssignedManager(user);
        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void updateTaskStatus(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).get();
        task.setTaskStatus(taskDTO.getTaskStatus());
        taskRepository.save(task);
    }
}
