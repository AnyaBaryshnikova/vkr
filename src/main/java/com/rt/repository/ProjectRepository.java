package com.rt.repository;

import com.rt.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectname(String projectName);

    @Query(value = "select * from t_projects where id IN (select projects_id from t_user_projects where users_id = ?)",nativeQuery = true)
    List<Project> allProjectsByUser(Long id);

    @Query(value = "select * from t_projects join t_user_projects tup on t_projects.id = tup.projects_id where users_id = ? and status = 'Новый'",nativeQuery = true)
    List<Project> allNewProjectsByUser(Long id);

    @Query(value = "select * from t_projects where status <> 'Закрыт'",nativeQuery = true)
    List<Project> allActiveProjects();

    @Query(value = "select * from t_projects where status = 'Новый'",nativeQuery = true)
    List<Project> allNewProjects();

    @Query(value = "select * from t_projects where status = 'В работе'",nativeQuery = true)
    List<Project> allOpenProjects();

    @Query(value = "select * from t_projects where status = 'Закрыт'",nativeQuery = true)
    List<Project> allClosedProjects();

    @Query(value = "select * from t_projects join t_user_projects tup on t_projects.id = tup.projects_id where users_id = ? and status = 'Открыт'",nativeQuery = true)
    List<Project> allOpenProjectsByUser(Long id);

    @Query(value = "select * from t_projects join t_user_projects tup on t_projects.id = tup.projects_id where users_id = ? and status = 'Закрыт'",nativeQuery = true)
    List<Project> allClosedProjectsByUser(Long id);

    @Query(value = "select projectname from t_projects where id = ?", nativeQuery = true)
    String projectName(Long id);

    @Query(value = "select * from t_projects where id = ?", nativeQuery = true)
    Project projectById(Long id);

    @Query(value = "select sum(tt.time_fact) from t_projects join t_projects_tasks tpt on t_projects.id = tpt.projects_id join t_tasks tt on tpt.tasks_id = tt.id where projects_id = ?", nativeQuery = true)
    Integer projectTime(Long id);

    @Query(value = "select t_projects.id from t_projects join t_projects_tasks tpt on " +
            "t_projects.id = tpt.projects_id join t_tasks tt on tt.id = tpt.tasks_id where tasks_id = ?", nativeQuery = true)
    Long projIdByTask(Long taskId);




}
