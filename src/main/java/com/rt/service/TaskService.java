package com.rt.service;

import com.rt.entity.Task;
import com.rt.repository.ProjectRepository;
import com.rt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TaskService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    public Task addTask(Task task) { return taskRepository.save(task); }

    public Task getByName(String name) {
        return taskRepository.findByTaskname(name);
    }


    public Task getTaskByID(Long id){
        return taskRepository.getTaskBYID(id);
    }

    public List<Task> allTasks(){
        return taskRepository.allTasksWithProjectNameAndUser();
    }

    public List<Task> allActiveTasks(){
        return taskRepository.allActiveTasks();
    }

    public List<Task> allActiveTasksByUser(Long id){
        return taskRepository.allActiveTasksByUser(id);
    }

    public List<Task> allTasksByProject(Long id){
        return taskRepository.allTasksByProject(id);
    }

    public List<Task> allTasksByUser(Long userId) {
        return taskRepository.allTasksByUser(userId);
    }

    @Transactional
    public void addTaskTime(Integer time, Long id){
        em.createNativeQuery("update t_tasks set time_fact = ?1 where id = ?2")
                .setParameter(1, time)
                .setParameter(2, id)
                .executeUpdate();
    }

    @Transactional
    public void addTaskToProject(Long prId, Long taskId){
        em.createNativeQuery("INSERT INTO t_projects_tasks VALUES (?1, ?2)")
                .setParameter(1, prId)
                .setParameter(2, taskId)
                .executeUpdate();

    }

    @Transactional
    public void addTaskToUser(Long userId, Long taskId){
        em.createNativeQuery("INSERT INTO t_user_tasks VALUES (?1, ?2)")
                .setParameter(1, userId)
                .setParameter(2, taskId)
                .executeUpdate();
    }

    @Transactional
    public void changeTaskStatusToNew(Long id){
        em.createNativeQuery("update t_tasks set status = 'Новый' where id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Transactional
    public void changeTaskStatusToOpen(Long id){
        em.createNativeQuery("update t_tasks set status = 'В работе' where id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Transactional
    public void changeTaskStatusToClosed(Long id){
        em.createNativeQuery("update t_tasks set status = 'Закрыт' where id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }
}
