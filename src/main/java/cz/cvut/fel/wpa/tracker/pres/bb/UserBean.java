package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.dto.UserDto;
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
//@Scope("re")
public class UserBean {

    @Autowired
    private UserService userService;

    public List<UserDto> getUsers() { return userService.getAllUsers(); }
}
