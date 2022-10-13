package com.rt.controller;

import com.rt.entity.Role;
import com.rt.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    @GetMapping("/projects")
    public String taskList(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object[] roles = user.getRoles().toArray();
        for(int i = 0; i < roles.length; ++i)
        {
            Role role = (Role)roles[i];
            if(role.getId().equals(new Long(2)))
                return "redirect:/projectsadmin";
        }

        return "redirect:/projectsuser";
    }
}
