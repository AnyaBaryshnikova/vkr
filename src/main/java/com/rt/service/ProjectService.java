package com.rt.service;

import com.rt.entity.Project;
import com.rt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@Service
public class ProjectService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    ProjectRepository projectRepository;

    public boolean projectNotExist(Project project) {
        if (projectRepository.findByProjectname(project.getProjectname()) == null)
            return true;
        return false;
    }

    public Project addProject(Project project) {
        Project pr = projectRepository.save(project);

        return pr;
    }

    public Project getProjectByID(Long id) {
        return projectRepository.projectById(id);
    }

    public Project getByName(String name) {
        return projectRepository.findByProjectname(name);
    }

    public Project editProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    public Long projIdByTask(Long taskId) {
        return projectRepository.projIdByTask(taskId);
    }

    public List<Project> allProjects() {
        return projectRepository.findAll();
    }

    public List<Project> allActiveProjects() {
        return projectRepository.allActiveProjects();
    }
    public List<Project> allNewProjects() {
        return projectRepository.allNewProjects();
    }
    public List<Project> allOpenProjects() {
        return projectRepository.allOpenProjects();
    }
    public List<Project> allClosedProjects() {
        return projectRepository.allClosedProjects();
    }

    public List<Project> allProjectsByUser(Long userId) {
        return projectRepository.allProjectsByUser(userId);
    }

    public Integer projectTime(Long projId) {
        return projectRepository.projectTime(projId);
    }


    @Transactional
    public void addProjectToUser(Long userId, Long taskId) {
        em.createNativeQuery("INSERT INTO t_user_projects VALUES (?1, ?2)")
                .setParameter(1, userId)
                .setParameter(2, taskId)
                .executeUpdate();
    }

    @Transactional
    public void changeProjectStatusToOpen(Long id) {
        em.createNativeQuery("update t_projects set status = 'В работе' where id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Transactional
    public void updateProjTime(Long id, int time) {
        em.createNativeQuery("update t_projects set timefact = ?1 where id = ?2")
                .setParameter(1, time)
                .setParameter(2, id)
                .executeUpdate();
    }

    @Transactional
    public void changeProjectStatusToClosed(Long id) {
        em.createNativeQuery("update t_projects set status = 'Закрыт' where id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Transactional
    public void changeProjectStatusToNew(Long id) {
        em.createNativeQuery("update t_projects set status = 'Новый' where id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Transactional
    public void addDateToProject(Project project) {
        em.createNativeQuery("UPDATE restrack.t_projects t SET t.end_date = CONVERT(DATETIME, ?1), t.start_date = CONVERT(DATETIME, ?2) WHERE t.id = ?3")
                .setParameter(1, project.getEndDate())
                .setParameter(2, project.getStartDate())
                .setParameter(3, project.getId())
                .executeUpdate();
    }

    @Transactional
    public void editmanager(Long projectId, String manager) {
        em.createNativeQuery(" UPDATE restrack.t_projects t SET t.manager = ?1 WHERE t.id = ?2")
                .setParameter(1, manager)
                .setParameter(2, projectId)
                .executeUpdate();
    }

    @Transactional
    public void editEndDate(Long id, Date endDate) {
        em.createNativeQuery("UPDATE restrack.t_projects t SET t.end_date = ?1 WHERE t.id = ?2")
                .setParameter(1, endDate)
                .setParameter(2, id)
                .executeUpdate();
    }

    @Transactional
    public void updateTimePlan(Long id, Date endDate) {
        Project proj = getProjectByID(id);

        Long timeplan = proj.getAmountOfWorkingDays() * 8;

        em.createNativeQuery("UPDATE restrack.t_projects t SET t.timeplan = ?1 WHERE t.id = ?2")
                .setParameter(1, timeplan)
                .setParameter(2, id)
                .executeUpdate();
    }
}
