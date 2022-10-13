package com.rt.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "t_projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Не меньше 5 знаков")
    private String projectname;

    @Column
    private String status;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private Date startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private Date endDate;


    @Column
    private String manager;

    @Column
    private Long timefact;

    @Column
    private Long timeplan;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users;

    public Project() {
    }

    public Project(String name, String manager) {
        this.projectname = name;
        this.status = "Новый";
        this.manager = manager;
    }

    public Project(String projectname, Date startDate, Date endDate, String manager) {
        this.projectname = projectname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager = manager;
        this.status = "Новый";
        this.timefact = new Long(0);
        if(!endDate.after(startDate))
        {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        java.util.Date temp = new java.util.Date();
        java.sql.Date currentDate = new Date(temp.getTime());
        if(!endDate.after(currentDate))
            this.status = "Закрыт";


        this.timeplan = getAmountOfWorkingDays() * 8;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Long getTimefact() {
        return timefact;
    }

    public void setTimefact(Long timefact) {
        this.timefact = timefact;
    }

    public Long getTimeplan() {
        return timeplan;
    }

    public void setTimeplan(Long timeplan) {
        this.timeplan = timeplan;
    }


    public Long getAmountOfWorkingDays(){
        Long wd = 0L;

        String data = getStartDate().toString();
        String end = getEndDate().toString();
        Date sdate = Date.valueOf(data);
        Date edate = Date.valueOf(end);

        while(sdate.compareTo(edate) <= 0)
        {
            if(getDayOfWeek(sdate) != 0 && getDayOfWeek(sdate) != 6)
                ++wd;
            sdate.setDate(sdate.getDate() + 1);
        }

        return wd - 2;
    }
     private int getDayOfWeek(Date day){
         Calendar cal = Calendar.getInstance();
         cal.setTime(day);
         return cal.get(Calendar.DAY_OF_WEEK);
     }

}