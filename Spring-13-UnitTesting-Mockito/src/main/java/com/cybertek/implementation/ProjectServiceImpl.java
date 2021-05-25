package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.User;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.ProjectRepository;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import com.cybertek.utils.Status;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private UserMapper userMapper;
    private UserService userService;
    private TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserMapper userMapper, UserService userService, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projects = projectRepository.findAll(Sort.by("projectCode"));
        return projects.stream().map(project -> projectMapper.convertToDto(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        return projectMapper.convertToDto(project);
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN); // setting status
        Project project = projectMapper.convertToEntity(projectDTO); // converting to com.cybertek.entity
        projectRepository.save(project); // saving com.cybertek.entity in DB
    }

    @Override
    public void update(ProjectDTO projectDTO) {

        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());
        Project updatedProject = projectMapper.convertToEntity(projectDTO);
        updatedProject.setId(project.getId());
        updatedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(updatedProject);

    }

    @Override
    public void delete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setDeleted(true);
        project.setProjectCode(project.getProjectCode()+"-"+project.getId());
        projectRepository.save(project);
        taskService.deleteByProject(projectMapper.convertToDto(project));

    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectDTO> getProjectsByManager(User user) {
//        LINES COMMENTED OUT AFTER ADDING USER AS A PARAMETER
//        UserDTO currentUser = userService.findByUserName("adel1986@yandex.ru");
//        User user = userMapper.convertToEntity(currentUser);
        List<ProjectDTO> projects = projectRepository.findAllByAssignedManager(user).stream().map(project -> {
            ProjectDTO projectDTO = projectMapper.convertToDto(project);
            projectDTO.setActiveTaskCount(taskService.getCountActive(project.getProjectCode()));
            projectDTO.setCompleteTaskCount(taskService.getCountCompleted(project.getProjectCode()));
            return projectDTO;
            }).collect(Collectors.toList());
        return projects;

    }
}
