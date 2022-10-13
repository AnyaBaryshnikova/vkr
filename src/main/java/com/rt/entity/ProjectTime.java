package com.rt.entity;

public class ProjectTime {

    private Project project;
    private Integer time;

    public ProjectTime() {
    }

    public ProjectTime(Project project, Integer time) {
        this.project = project;
        this.time = time;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
