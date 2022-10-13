package com.rt.controller;

import com.rt.entity.Project;
import com.rt.entity.Task;
import com.rt.service.ProjectService;
import com.rt.service.TaskService;
import com.rt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DashboardEmployeesController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/employeedashboard")
    public String userList(@RequestParam(defaultValue = "31") String selectU,
                           @RequestParam(defaultValue = "60") String selectP,
                           Model model) {

        HashMap<String, Integer> taskTimeProjectMap = new HashMap<>();
        List<Task> userTasks = taskService.allTasksByUser(Long.valueOf(selectU));
        List<String> userTask = new ArrayList<>();
        List<Integer> userFact = new ArrayList<>();
        List<Integer> userPlan = new ArrayList<>();
        for(int i = 0; i < userTasks.size(); ++i)
        {
            String pn = ((Project) userTasks.get(i).getProjects().toArray()[0]).getProjectname();
            if(!taskTimeProjectMap.containsKey(pn))
                taskTimeProjectMap.put(pn, 0);

            taskTimeProjectMap.replace(pn,
                    taskTimeProjectMap.get(pn) +
                            userTasks.get(i).getTimeFact());


            Long prId = ((Project)userTasks.get(i).getProjects().toArray()[0]).getId();
            if(prId.equals(Long.valueOf(selectP))) {
                userTask.add(userTasks.get(i).getTaskname());
                userFact.add(userTasks.get(i).getTimeFact());
                userPlan.add(userTasks.get(i).getTimePlan());
            }
        }

        model.addAttribute("userTasks", userTask);
        model.addAttribute("factUser", userFact);
        model.addAttribute("planUser", userPlan);
        model.addAttribute("pname", projectService.getProjectByID(Long.valueOf(selectP)).getProjectname());
        model.addAttribute("username", userService.findUserById(Long.valueOf(selectU)).getUsername());
        model.addAttribute("projectTimes", new ArrayList<>(taskTimeProjectMap.values()));
        model.addAttribute("projNames", new ArrayList<>(taskTimeProjectMap.keySet()));

        return "employeedashboard";
    }


}
