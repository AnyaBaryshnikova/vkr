package com.rt.controller;

import com.rt.entity.Project;
import com.rt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class DashboardAllProjController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/allprojdashboard")
    public String userList(@RequestParam(defaultValue = "2022-05-14") String startDate,
                           @RequestParam(defaultValue = "Текущий месяц") String period,
                           Model model) {

        List<Project> allproj = projectService.allActiveProjects();
        Date date = Date.valueOf(startDate);
        List<String> projects = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        List<Long> plantimes = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        java.util.Date temp = new java.util.Date();
        java.sql.Date currentDate = new Date(temp.getTime());
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(currentDate);

        if(period == null)
            period = "Текущий месяц";


        switch (period) {
            case "1 квартал":
                for (int i = 0; i < allproj.size(); ++i) {
                    Date data = allproj.get(i).getStartDate();
                    calendar.setTime(data);
                if ((calendar.get(Calendar.MONTH) == 0 || calendar.get(Calendar.MONTH) == 1 || calendar.get(Calendar.MONTH) == 2) && (calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR))) {
                    projects.add(allproj.get(i).getProjectname());
                    times.add(allproj.get(i).getTimefact());
                    plantimes.add(allproj.get(i).getTimeplan());
                }
            }
                model.addAttribute("sdate", "Проекты за 1 квартал");
                break;
            case "2 квартал":
                for (int i = 0; i < allproj.size(); ++i) {
                    Date data = allproj.get(i).getStartDate();
                    calendar.setTime(data);
                    if ((calendar.get(Calendar.MONTH) == 3 || calendar.get(Calendar.MONTH) == 4 || calendar.get(Calendar.MONTH) == 5) && (calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR))) {
                        projects.add(allproj.get(i).getProjectname());
                        times.add(allproj.get(i).getTimefact());
                        plantimes.add(allproj.get(i).getTimeplan());
                    }
                }
                model.addAttribute("sdate", "Проекты за 2 квартал");
                break;
            case "3 квартал":
                for (int i = 0; i < allproj.size(); ++i) {
                    Date data = allproj.get(i).getStartDate();
                    calendar.setTime(data);
                    if ((calendar.get(Calendar.MONTH) == 6 || calendar.get(Calendar.MONTH) == 7 || calendar.get(Calendar.MONTH) == 8) && (calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR))) {
                        projects.add(allproj.get(i).getProjectname());
                        times.add(allproj.get(i).getTimefact());
                        plantimes.add(allproj.get(i).getTimeplan());
                    }
                }
                model.addAttribute("sdate", "Проекты за 3 квартал");
                break;
            case "4 квартал":
                for (int i = 0; i < allproj.size(); ++i) {
                    Date data = allproj.get(i).getStartDate();
                    calendar.setTime(data);
                    if ((calendar.get(Calendar.MONTH) == 9 || calendar.get(Calendar.MONTH) == 10 || calendar.get(Calendar.MONTH) == 11) && (calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR))) {
                        projects.add(allproj.get(i).getProjectname());
                        times.add(allproj.get(i).getTimefact());
                        plantimes.add(allproj.get(i).getTimeplan());
                    }

                    model.addAttribute("sdate", "Проекты за 4 квартал");
                }
                break;
            case "Дата начала проекта не раньше":
                for (int i = 0; i < allproj.size(); ++i) {
                    if (allproj.get(i).getStartDate().compareTo(date) > 0) {
                        projects.add(allproj.get(i).getProjectname());
                        times.add(allproj.get(i).getTimefact());
                        plantimes.add(allproj.get(i).getTimeplan());
                    }
                }
                model.addAttribute("sdate", "Дата проекта не раньше: " + startDate);
                break;
            case "Текущий месяц":
                for (int i = 0; i < allproj.size(); ++i) {
                    Date data = allproj.get(i).getStartDate();
                    calendar.setTime(data);
                    if (calendar.get(Calendar.MONTH) == calendar1.get(Calendar.MONTH)  && calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR)) {
                        projects.add(allproj.get(i).getProjectname());
                        times.add(allproj.get(i).getTimefact());
                        plantimes.add(allproj.get(i).getTimeplan());
                    }
                }
                model.addAttribute("sdate", "Проекты за текущий месяц");
                break;
        }

        model.addAttribute("projects", projects);
        model.addAttribute("times", times);
        model.addAttribute("timesPlan", plantimes);

        return "allprojdashboard";
    }
}
