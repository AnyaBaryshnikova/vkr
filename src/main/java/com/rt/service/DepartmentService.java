package com.rt.service;

import com.rt.entity.Department;
import com.rt.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DepartmentService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    DepartmentRepository departmentRepository;

    public Department getDepartmentByName(String depName){
        return departmentRepository.findByDepname(depName);

    }

    public List<Department> allDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public void addDepToUser(Long userId, Long taskId){
        em.createNativeQuery("INSERT INTO restrack.t_user_departments (users_id, departments_id)\n" +
                "VALUES (?1, ?2);")
                .setParameter(1, userId)
                .setParameter(2, taskId)
                .executeUpdate();
    }

    @Transactional
    public void updateDepToUser(Long userId, Long depId){
        em.createNativeQuery("UPDATE restrack.t_user_departments set departments_id = ?1 where users_id = ?2")
                .setParameter(1, depId)
                .setParameter(2, userId)
                .executeUpdate();
    }
}
