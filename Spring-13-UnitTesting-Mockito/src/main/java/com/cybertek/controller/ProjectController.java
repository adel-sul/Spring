package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.User;
import com.cybertek.mapper.UserMapper;
import com.cybertek.service.ProjectService;
import com.cybertek.service.UserService;
import com.cybertek.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;
    UserService userService;
    UserMapper userMapper;

    public ProjectController(ProjectService projectService, UserService userService, UserMapper userMapper) {
        this.projectService = projectService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/create")
    public String projectCreate(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.findByRole("manager"));
        return "project/create";
    }

    @PostMapping("/create")
    public String projectSave(ProjectDTO project, Model model) {
        project.setProjectStatus(Status.OPEN);
        projectService.save(project);
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectcode}")
    public String userEdit(@PathVariable("projectcode") String projectCode, Model model) {
        model.addAttribute("project", projectService.findByProjectCode(projectCode));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.findByRole("manager"));

        return "project/update";
    }

    @PostMapping("/update/{projectcode}")
    public String userUpdate(@PathVariable("projectcode") String projectCode, ProjectDTO project, Model model) {
        projectService.update(project);
        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectcode}")
    public String deleteUser(@PathVariable("projectcode") String code) {
        projectService.delete(code);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectCode) {
        projectService.complete(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/manager/complete")
    public String getProjectByManager(Model model) {
        User user = userMapper.convertToEntity(userService.findByUserName("adel1986@yandex.ru"));
        List<ProjectDTO> projects = projectService.getProjectsByManager(user);
        model.addAttribute("projects", projects);
        return "/manager/project-status";
    }

    @GetMapping("/manager/complete/{projectcode}")
    public String managerCompleted(@PathVariable("projectcode") String projectCode) {
        projectService.complete(projectCode);
        return "redirect:/project/manager/complete";

    }
}
