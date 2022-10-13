package com.rt.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String taskname;

    @Column
    private String status;

    @Column
    private Integer timePlan;

    @Column
    private Integer timeFact;

    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;

    @ManyToMany(mappedBy = "tasks")
    private Set<Project> projects;

    public Task() {
    }

    public Task(String taskname, Integer timePlan) {
        this.taskname = taskname;
        this.timePlan = timePlan;
        this.status = "Новый";
        this.timeFact = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTimePlan() {
        return timePlan;
    }

    public void setTimePlan(Integer timePlan) {
        this.timePlan = timePlan;
    }

    public Integer getTimeFact() {
        return timeFact;
    }

    public void setTimeFact(Integer timeFact) {
        this.timeFact = timeFact;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}