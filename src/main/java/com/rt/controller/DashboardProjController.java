package com.rt.controller;

import com.rt.entity.Task;
import com.rt.service.ProjectService;
import com.rt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardProjController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projdashboard")
    public String userList(@RequestParam(defaultValue = "60") String selectProject,
                           Model model) {

        List<Task> allTasks = taskService.allTasksByProject(Long.valueOf(selectProject));
        List<String> tasks = new ArrayList<>();
        List<Integer> taskFact = new ArrayList<>();
        List<Integer> taskPlan = new ArrayList<>();
        for (int i = 0; i < allTasks.size(); ++i) {
            tasks.add(allTasks.get(i).getTaskname());
            taskFact.add(allTasks.get(i).getTimeFact());
            taskPlan.add(allTasks.get(i).getTimePlan());
        }

        model.addAttribute("allTasks", tasks);
        model.addAttribute("factTaskTime", taskFact);
        model.addAttribute("planTaskTime", taskPlan);
        model.addAttribute("prname", projectService.getProjectByID(Long.valueOf(selectProject)).getProjectname());

        return "projdashboard";
    }

}
