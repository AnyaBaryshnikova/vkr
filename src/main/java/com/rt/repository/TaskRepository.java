package com.rt.repository;

import com.rt.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskname(String taskName);

    @Query(value = "select * from t_tasks join t_user_tasks tut on t_tasks.id = tut.tasks_id join t_projects_tasks tpt on t_tasks.id = tpt.tasks_id where users_id = ?",nativeQuery = true)
    List<Task> allTasksByUser(Long id);

    @Query(value = "select * from t_tasks where id = ?)",nativeQuery = true)
    Task getTaskBYID(Long id);

    @Query(value = "select tt.*, t_projects.projectname, tu.username from t_tasks tt\n" +
            "                join t_projects_tasks tpt on tt.id = tpt.tasks_id\n" +
            "                join t_projects on tpt.projects_id = t_projects.id\n" +
            "            join t_user_tasks tus on tt.id = tus.tasks_id\n" +
            "            join t_user tu on tu.id = tus.users_id",nativeQuery = true)
    List<Task> allTasksWithProjectNameAndUser();

    @Query(value = "select tt.*, t_projects.projectname, tu.username from t_tasks tt\n" +
            "    join t_projects_tasks tpt on tt.id = tpt.tasks_id\n" +
            "    join t_projects on tpt.projects_id = t_projects.id\n" +
            "join t_user_tasks tus on tt.id = tus.tasks_id\n" +
            "join t_user tu on tu.id = tus.users_id\n" +
            "where tu.id = ?",nativeQuery = true)
    List<Task> allTasksWithProjectNameByUser(Long id);

    @Query(value = "select * from t_tasks where status <> 'Закрыт'",nativeQuery = true)
    List<Task> allActiveTasks();

    @Query(value = "select * from t_tasks join t_user_tasks tut on t_tasks.id = tut.tasks_id join t_projects_tasks tpt on t_tasks.id = tpt.tasks_id join t_projects tp on tp.id = tpt.projects_id where t_tasks.status <> 'Закрыт' and users_id = ?",nativeQuery = true)
    List<Task> allActiveTasksByUser(Long id);

    @Query(value = "\n" +
            "select * from t_tasks join t_projects_tasks tpt on t_tasks.id = tpt.tasks_id join t_projects tp on tp.id = tpt.projects_id where projects_id = ?",nativeQuery = true)
    List<Task> allTasksByProject(Long id);




}
