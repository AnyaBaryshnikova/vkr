package com.rt.controller;

import com.rt.entity.User;
import com.rt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectsUserController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projectsuser")
    public String projectList(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();
        model.addAttribute("allProjects", projectService.allProjectsByUser(id));
        return "projectsuser";
    }
}
