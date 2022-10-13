package com.rt.repository;

import com.rt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select tu.*, depname, projectname from t_user tu join t_user_departments tud on tu.id = tud.users_id join t_departments td on td.id = tud.departments_id join t_user_projects tup on tud.users_id = tup.users_id join t_projects tp on tp.id = tup.projects_id",nativeQuery = true)
    List<User> userProjectDepartment();

    @Query(value = "select * from t_user join t_user_projects tup on t_user.id = tup.users_id join t_projects tp on tp.id = tup.projects_id",nativeQuery = true)
    List<User> userProject();

    @Query(value = "INSERT INTO t_user_projects VALUES (:projectId, :userId)",nativeQuery = true)
    List<User> addUserToProject(@Param("projectId")Long projectId, @Param("userId") Long userId);

    @Query(value = "SELECT * FROM t_user_projects WHERE projects_id = :projectId AND users_id = :userId",nativeQuery = true)
    List<User> checkUserToProjectexists(@Param("projectId")Long projectId, @Param("userId") Long userId);

    @Query(value = "select depname from t_user join t_user_departments tud on t_user.id = tud.users_id join t_departments td on td.id = tud.departments_id where users_id = ?",nativeQuery = true)
    String userDepartment(Long userId);

    @Query(value = "select * from t_user where position = 'junior'",nativeQuery = true)
    List<User> allJunUsers();

    @Query(value = "select * from t_user where position = 'senior'",nativeQuery = true)
    List<User> allSenUsers();

    @Query(value = "select * from t_user where position = 'leader'",nativeQuery = true)
    List<User> allLedUsers();


}