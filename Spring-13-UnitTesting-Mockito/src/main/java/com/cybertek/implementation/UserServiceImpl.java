package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.exception.TicketingProjectException;
import com.cybertek.mapper.MapperUtil;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import com.cybertek.utils.Status;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    ProjectService projectService;
    TaskService taskService;
    private MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, @Lazy ProjectService projectService, TaskService taskService, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.projectService = projectService;
        this.taskService = taskService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> users = userRepository.findAll(Sort.by("firstName"));
        return users.stream().map(user -> mapperUtil.convert(user, new UserDTO())).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String name) {
        User user = userRepository.findByUserName(name);
        return mapperUtil.convert(user, new UserDTO());
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = mapperUtil.convert(userDTO, new User());
        userRepository.save(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        // find current user in DB
        User user = userRepository.findByUserName(userDTO.getUserName());
        // map update user com.cybertek.dto to com.cybertek.entity object
        User updatedUser = mapperUtil.convert(userDTO, new User());
        // set id to the converted object
        updatedUser.setId(user.getId());
        // save updated user in DB
        userRepository.save(updatedUser);

        return findByUserName(userDTO.getUserName());
    }

    @Override
    public void deleteByUserName(String userName) {
        // hard delete
        userRepository.deleteByUserName(userName);
    }

    @Override
    public void delete(String userName) throws TicketingProjectException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new TicketingProjectException("User Does Not Exist");
        }
        if (!checkIfUserCanBeDeleted(user)) {
            if (user.getRole().getDescription().equalsIgnoreCase("employee")) {
                throw new TicketingProjectException("Employee Can not be deleted. Task(s) in Progress");
            } else if (user.getRole().getDescription().equalsIgnoreCase("manager")) {
                throw new TicketingProjectException("Manager Can not be deleted. Project(s) Not Completed");
            }
        }

        user.setUserName(user.getUserName() + "-" + user.getId());
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findByRole(String role) {
        List<User> users = userRepository.findAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(user -> mapperUtil.convert(user, new UserDTO())).collect(Collectors.toList());
    }

    @Override
    public boolean checkIfUserCanBeDeleted(User user) {
        switch (user.getRole().getDescription()) {
            case "Manager":
                List<ProjectDTO> projects = projectService.getProjectsByManager(user);
                return projects.size() == 0;
            case "Employee":
                List<TaskDTO> tasks = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
                return tasks.size() == 0;
            default:
                return true;
        }
    }
}
