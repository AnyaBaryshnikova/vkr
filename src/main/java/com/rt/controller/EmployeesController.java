package com.rt.controller;

import com.rt.entity.Department;
import com.rt.entity.User;
import com.rt.service.DepartmentService;
import com.rt.service.ProjectService;
import com.rt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeesController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees")
    public String taskList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allUserst", userService.allUsers());
        model.addAttribute("alldeps", departmentService.allDepartments());
        return "employees";
    }

    @PostMapping("/insertUserToDepartment")
    public String addUserToDepartment(@RequestParam String selectUser,
                                      @RequestParam(required = false) String selectDep,
                                      @RequestParam(required = false) String rate,
                                      @RequestParam(required = false) String position, Model model) {

        if (selectUser.equals("Пользователь"))
            return "redirect:/employees";

        Long userId = new Long(selectUser);
        User tuser = userService.findUserById(userId);

        if (rate == "Не изменять")
            rate = null;
        if(position == "Не изменять")
            position = null;

        if(position != null && rate != null){
            userService.updatePosition(userId, position);
            userService.updateRate(userId, rate);
        }
        else if(position == null && rate != null)
            userService.updateRate(userId, rate);

        else if(rate == null && position != null)
            userService.updatePosition(userId, position);

        if(!selectDep.equals("Отдел")) {
            Department dep = departmentService.getDepartmentByName(selectDep);

            Long depId = dep.getId();
            String userDep = tuser.getDepName();

            if (userDep == null) {
                departmentService.addDepToUser(userId, depId);
                return "redirect:/employees";
            }
            if (userDep != dep.getDepname()) {
                departmentService.updateDepToUser(userId, depId);
            }
        }

        return "redirect:/employees";
    }
}
