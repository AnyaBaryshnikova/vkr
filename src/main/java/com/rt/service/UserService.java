package com.rt.service;

import com.rt.entity.*;
import com.rt.repository.ProjectRepository;
import com.rt.repository.RoleRepository;
import com.rt.repository.TaskRepository;
import com.rt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public List<User> allJunUsers() {
        return userRepository.allJunUsers();
    }

    public List<User> allSenUsers() {
        return userRepository.allSenUsers();
    }

    public List<User> allLedUsers() {
        return userRepository.allLedUsers();
    }


    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public List<User> allUsersWithDep(){
        List<User> users = userRepository.userProjectDepartment();
        Set<User> set = new HashSet<>(users);
        users.clear();
        users.addAll(set);
        return users;
    }

    public List<User> usersProjects(){
        return userRepository.userProject();
    }

    public void addUserToProject(Long projectId, Long userId){
        if(userRepository.checkUserToProjectexists(projectId, userId) == null)
            userRepository.addUserToProject(projectId, userId);
    }

    public List<UserInfo> getUserInfo(){
        List<UserInfo> userInfo = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for(int i = 0; i < users.size(); ++i){
            List<Project> allProjects = projectRepository.allProjectsByUser(users.get(i).getId());
            List<Project> newProj = projectRepository.allNewProjectsByUser(users.get(i).getId());
            List<Project> openProj = projectRepository.allOpenProjectsByUser(users.get(i).getId());
            List<Project> closedProj = projectRepository.allClosedProjectsByUser(users.get(i).getId());

            String userName = users.get(i).getUsername();
            String department = userRepository.userDepartment(users.get(i).getId());
            int projects = projectRepository.allProjectsByUser(users.get(i).getId()).size();
            int newProjects = newProj.size();
            int openProjects = openProj.size();
            int closedProjects = closedProj.size();

            List<Task> task = taskRepository.allTasksByUser(users.get(i).getId());
            int tasksNumber = task.size();
            String tasks = "";
            int openTime = 0;
            int closedTime = 0;

            for(int j = 0; j < task.size(); ++j) {
                tasks += task.get(j).getTaskname() + ";";

                switch (task.get(j).getStatus()) {
                    case  ("В работе"):
                        openTime += task.get(j).getTimeFact();
                        break;
                    case  ("Закрыт"):
                        openTime += task.get(j).getTimeFact();
                        break;
                    default:
                        break;
                }
            }
            int fullTime = openTime + closedTime;

            userInfo.add(new UserInfo(userName,department, projects, newProjects, openProjects, closedProjects, tasksNumber, tasks, fullTime, openTime, closedTime));
        }

        return userInfo;
    }

    @Transactional
    public void updateRate(Long userId, String rate) {
        em.createNativeQuery("update t_user set rate = ?1  where id = ?2")
                .setParameter(1, rate)
                .setParameter(2, userId)
                .executeUpdate();
    }

    @Transactional
    public void updatePosition(Long userId, String position) {
        em.createNativeQuery("update t_user set position = ?1 where id = ?2")
                .setParameter(1, position)
                .setParameter(2, userId)
                .executeUpdate();
    }
}