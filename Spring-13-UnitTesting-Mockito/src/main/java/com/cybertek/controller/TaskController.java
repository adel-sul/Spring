package com.cybertek.controller;

import com.cybertek.dto.TaskDTO;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;

import com.cybertek.utils.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    ProjectService projectService;
    UserService userService;
    TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String taskCreate(Model model) {
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("employees", userService.findByRole("employee"));
        model.addAttribute("tasks", taskService.listAllTasks());
        return "task/create";
    }

    @PostMapping("/create")
    public String taskUpdate(TaskDTO task, Model model) {
        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id) {
        taskService.delete(id);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{id}")
    public String taskEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("employees", userService.findByRole("employee"));
        model.addAttribute("tasks", taskService.listAllTasks());

        return "task/update";
    }

    @PostMapping("/update/{id}")
    public String taskUpdate(@PathVariable("id") Integer id, TaskDTO task, Model model) {
        taskService.update(task);
        return "redirect:/task/create";
    }

    @GetMapping("/employee")
    public String edit(Model model) {
        List<TaskDTO> tasks = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        model.addAttribute("tasks", tasks);
        return "task/employee-tasks";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeePendingTasks(@PathVariable("id") Integer id, Model model) {
        TaskDTO taskDTO = taskService.findById(id);
        List<TaskDTO> tasks = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);

        model.addAttribute("task", taskDTO);
        model.addAttribute("users", userService.findByRole("employee"));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("tasks", tasks);
        model.addAttribute("statuses", Status.values());

        return "task/employee-update";
    }

    @PostMapping("/employee/update/{id}")
    public String employeeTaskUpdate(@PathVariable("id") Integer id, TaskDTO task) {
        taskService.updateTaskStatus(task);
        return "redirect:/task/employee";
    }

    @GetMapping("/employee/archive")
    public String employeeArchive (Model model) {
        List<TaskDTO> tasks = taskService.listAllTasksByStatus(Status.COMPLETE);
        model.addAttribute("tasks", tasks);
        return "task/employee-archive";
    }
}
