package com.rt.controller;

import com.rt.service.ProjectService;
import com.rt.service.TaskService;
import com.rt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardAdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/dashboardadmin")
    public String userList(@RequestParam(defaultValue = "41") String selectProject,
                           @RequestParam(defaultValue = "34") String selectU,
                           @RequestParam(defaultValue = "41") String selectP,
                           Model model) {

        model.addAttribute("allProjamount", projectService.allProjects().size());
        model.addAttribute("newProjamount", projectService.allNewProjects().size());
        model.addAttribute("openProjamount", projectService.allOpenProjects().size());
        model.addAttribute("closedProjamount", projectService.allClosedProjects().size());
        model.addAttribute("allUsersamount", userService.allUsers().size());
        model.addAttribute("junUsersamount", userService.allJunUsers().size());
        model.addAttribute("senUsersamount", userService.allSenUsers().size());
        model.addAttribute("leadUsersamount", userService.allLedUsers().size());
        model.addAttribute("allProjects", projectService.allProjects());
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("allPro", projectService.allProjects());
        model.addAttribute("allP", projectService.allProjects());
        model.addAttribute("allU", userService.allUsers());

        return "dashboardadmin";
    }

    @PostMapping("/allProjReport")
    public String showAllProjects(@RequestParam String startDate,
                                  @RequestParam(defaultValue = "Текущий месяц") String period,
                                  RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("startDate", startDate);
        redirectAttributes.addAttribute("period", period);
        return "redirect:/allprojdashboard";

    }

    @PostMapping("/showAllTasks")
    public String showAllTasks(@RequestParam String selectProject,
                                  RedirectAttributes redirectAttributes) {

        if(selectProject.equals("Проект"))
            return "redirect:/dashboardadmin";

        redirectAttributes.addAttribute("selectProject", selectProject);
        return "redirect:/projdashboard";

    }

    @PostMapping("/showAllUsers")
    public String showAllUsers(@RequestParam String selectU, @RequestParam String selectP,
                               RedirectAttributes redirectAttributes) {
        if(selectU.equals("Пользователь") || selectP.equals("Проект"))
            return "redirect:/dashboardadmin";

        redirectAttributes.addAttribute("selectU", selectU);
        redirectAttributes.addAttribute("selectP", selectP);
        return "redirect:/employeedashboard";

    }
}
