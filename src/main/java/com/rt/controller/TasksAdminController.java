package com.rt.controller;

import com.rt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksAdminController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasksadmin")
    public String taskList(Model model) {
        model.addAttribute("allTasks", taskService.allTasks());
        return "tasksadmin";
    }

}
