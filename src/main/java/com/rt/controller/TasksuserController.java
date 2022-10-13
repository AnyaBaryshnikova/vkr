package com.rt.controller;

import com.rt.entity.Project;
import com.rt.entity.Task;
import com.rt.entity.User;
import com.rt.service.ProjectService;
import com.rt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TasksuserController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/tasksuser")
    public String taskList(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();
        model.addAttribute("allTasks", taskService.allTasksByUser(id));
        model.addAttribute("activeTasks", taskService.allActiveTasksByUser(id));
        model.addAttribute("allProjects", projectService.allProjectsByUser(id));
        return "tasksuser";
    }

    @PostMapping("/addTime")
    public String addProject(@RequestParam Integer taskTime,
                             @RequestParam Long taskId, Model model) {
        if(taskTime == null)
            return "redirect:/tasksuser";

        if(taskTime <= 0)
            return "redirect:/tasksuser";

        taskService.addTaskTime(taskTime, taskId);
        taskService.changeTaskStatusToOpen(taskId);
        return "redirect:/tasksuser";

    }

    @PostMapping("/insertTask")
    public String addTask(@RequestParam String newTaskName,
                          @RequestParam String selectProject,
                          @RequestParam String planTime,
                          Model model) {
        if(newTaskName == "")
            return "redirect:/tasksuser";

        if(selectProject == "Название проекта")
            return "redirect:/tasksuser";

        if(planTime == "")
            return "redirect:/tasksuser";

        Integer pTime = new Integer(planTime);

        Long pID =new Long(selectProject);
        Project project = projectService.getProjectByID(pID);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();

        Task task = new Task(newTaskName, pTime);
        taskService.addTask(task);
        Long projId = project.getId();
        Long taskId = task.getId();
        taskService.addTaskToProject(projId, taskId);
        taskService.addTaskToUser(id, taskId);

        return "redirect:/tasksuser";
    }


    @PostMapping("/editTask")
    public String editTask(@RequestParam String selectTask,
                           @RequestParam Integer factTime,
                           @RequestParam String status,
                           Model model) {

        if(selectTask.equals("Название задачи"))
            return "redirect:/tasksuser";
        Long taskId = new Long(selectTask);

        if(!status.equals("Статус задачи")){
            switch (status) {
                case  ("1"):
                    taskService.changeTaskStatusToNew(taskId);
                    break;
                case ("2"):
                    taskService.changeTaskStatusToOpen(taskId);
                    break;
                case ("3"):
                    taskService.changeTaskStatusToClosed(taskId);
                    break;

            }
        }

        if(factTime != null){
            taskService.addTaskTime(factTime, taskId);
            taskService.changeTaskStatusToOpen(taskId);

            Long projid = projectService.projIdByTask(taskId);
            int projTime = projectService.projectTime(projid);
            projectService.updateProjTime(projid, projTime);
        }

        return "redirect:/tasksuser";
    }
}
