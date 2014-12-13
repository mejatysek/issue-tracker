package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.dto.RoleDto;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import cz.cvut.fel.wpa.tracker.service.RoleService;
import cz.cvut.fel.wpa.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 13.12.14
 */
@Component("userBean")
@Scope("request")
public class UserBean {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    protected Long roleId;

    private String userName;

    private String email;

    private String password;

    public String addUser(){
        if(userService.addUser(userName, password, true, roleId, email) != null)
            return "success";
        else
            return "fail";
    }

    public List<UserDto> getUsers() { return userService.getAllUsers(); }

    public RoleDto getRole(Long id){ return roleService.getRoleById(id); }

    public void deactivateUser(Long id) { userService.deactivateUser(id); }

    public List<RoleDto> getRoles(){ return roleService.getAllRoles(); }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
