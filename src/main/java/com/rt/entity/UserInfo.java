package com.rt.entity;

public class UserInfo {

    private String userName;
    private String department;
    private int projects;
    private int newProjects;
    private int openProjects;
    private int closedProjects;
    private int tasksNumber;
    private String tasks;
    private int fullTime;
    private int openTime;
    private int closedTime;

    public UserInfo(String userName, String department, int projects, int newProjects, int openProjects, int closedProjects, int tasksNumber, String tasks, int fullTime, int openTime, int closedTime) {
        this.userName = userName;
        this.department = department;
        this.projects = projects;
        this.newProjects = newProjects;
        this.openProjects = openProjects;
        this.closedProjects = closedProjects;
        this.tasksNumber = tasksNumber;
        this.tasks = tasks;
        this.fullTime = fullTime;
        this.openTime = openTime;
        this.closedTime = closedTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProjects() {
        return projects;
    }

    public void setProjects(int projects) {
        this.projects = projects;
    }

    public int getNewProjects() {
        return newProjects;
    }

    public void setNewProjects(int newProjects) {
        this.newProjects = newProjects;
    }

    public int getOpenProjects() {
        return openProjects;
    }

    public void setOpenProjects(int openProjects) {
        this.openProjects = openProjects;
    }

    public int getClosedProjects() {
        return closedProjects;
    }

    public void setClosedProjects(int closedProjects) {
        this.closedProjects = closedProjects;
    }

    public int getTasksNumber() {
        return tasksNumber;
    }

    public void setTasksNumber(int tasksNumber) {
        this.tasksNumber = tasksNumber;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public int getFullTime() {
        return fullTime;
    }

    public void setFullTime(int fullTime) {
        this.fullTime = fullTime;
    }

    public int getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public int getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(int closedTime) {
        this.closedTime = closedTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
