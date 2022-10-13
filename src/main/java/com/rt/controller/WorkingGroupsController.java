package com.rt.controller;

import com.rt.service.DepartmentService;
import com.rt.service.ProjectService;
import com.rt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkingGroupsController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/workinggroups")
    public String taskList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allProjects", projectService.allActiveProjects());
        return "workinggroups";
    }

    @PostMapping("/addUserToProj")
    public String addTask(@RequestParam String selectUser, @RequestParam String selectProj, Model model) {
        if (selectUser.equals("Пользователь"))
            return "redirect:/workinggroups";

        if (selectProj.equals("Проект"))
            return "redirect:/workinggroups";

        Long userId = new Long(selectUser);
        Long projectId = new Long(selectProj);

        projectService.addProjectToUser(userId, projectId);

        return "redirect:/workinggroups";
    }
}