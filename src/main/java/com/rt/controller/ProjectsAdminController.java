package com.rt.controller;

import com.rt.entity.Project;
import com.rt.service.ProjectService;
import com.rt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProjectsAdminController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @GetMapping("/projectsadmin")
    public String projectsList(Model model) {
        model.addAttribute("allProjects", projectService.allProjects());
        model.addAttribute("users", userService.allUsers());
        return "projectsadmin";
    }

    @PostMapping("/insertProj")
    public String addProject(@RequestParam String newProjName, @RequestParam String manager,
                             @RequestParam String startDate,
                             @RequestParam String endDate,
                             Model model) {
        if (newProjName == "")
            return "redirect:/projectsadmin";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedate = null;
        try {
            parsedate = format.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parsedate == null)
            return "redirect:/projectsadmin";
        java.sql.Date start = new java.sql.Date(parsedate.getTime());


        try {
            parsedate = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parsedate == null)
            return "redirect:/projectsadmin";
        java.sql.Date end = new java.sql.Date(parsedate.getTime());

        if (manager.equals("Ответсвенный за проект"))
            return "redirect:/projectsadmin";

        Project project = new Project(newProjName, start, end, manager);
        if (projectService.projectNotExist(project))
            projectService.addProject(project);

        return "redirect:/projectsadmin";
    }

    @PostMapping("/editProj")
    public String editProject(@RequestParam String projectName,
                              @RequestParam String status,
                              @RequestParam String projmanager,
                              @RequestParam String endData,
                              Model model) {
        if (projectName.equals("Название проекта"))
            return "redirect:/projectsadmin";

        Long id = projectService.getByName(projectName).getId();

        if (status.equals("Новый"))
            projectService.changeProjectStatusToNew(id);
        else if (status.equals("В работе"))
            projectService.changeProjectStatusToOpen(id);
        else if (status.equals("Закрыт"))
            projectService.changeProjectStatusToClosed(id);

        if (!projmanager.equals("Ответственный за проект"))
            projectService.editmanager(id, projmanager);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedate = null;
        try {
            parsedate = format.parse(endData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parsedate != null) {
            java.sql.Date end = new java.sql.Date(parsedate.getTime());
            projectService.editEndDate(id, end);
            projectService.updateTimePlan(id, end);
        }

        return "redirect:/projectsadmin";
    }
}
