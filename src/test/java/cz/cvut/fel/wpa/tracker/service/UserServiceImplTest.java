/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.UserDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class UserServiceImplTest extends AbstractServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddAndRetrieveUser() {

        String userName = "UserName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";

        Long id = userService.addUser(userName, passwd, state, email);
        UserDto userDto = userService.getUserById(id);

        assertEquals(userName, userDto.getUserName());
        assertEquals(state, userDto.getState());
        assertEquals(email, userDto.getEmail());

    }

    @Test
    public void testDeactivateUser() {

        String userName = "UserName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";

        Long id = userService.addUser(userName, passwd, state, email);
        UserDto userDto = userService.getUserById(id);
        assertEquals(1, userService.getAllUsers().size());
        assertEquals(1, userService.getUserByState(true).size());
        assertEquals(0, userService.getUserByState(false).size());
        userService.deactivateUser(id);
        assertEquals(1, userService.getAllUsers().size());
        assertEquals(0, userService.getUserByState(true).size());
        assertEquals(1, userService.getUserByState(false).size());
    }

    @Test
    public void testEditUser() {

        String userName = "UserName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";

        Long id = userService.addUser(userName, passwd, state, email);
        UserDto userDto = userService.getUserById(id);
        assertEquals(userName, userDto.getUserName());
        assertEquals(state, userDto.getState());
        assertEquals(email, userDto.getEmail());

        String changedEmail = "abder@bdc.cz";
        userDto.setEmail(changedEmail);
        userService.editUser(userDto);
        userDto = userService.getUserById(id);

        assertEquals(userName, userDto.getUserName());
        assertEquals(state, userDto.getState());
        assertEquals(changedEmail, userDto.getEmail());
        assertEquals(1, userService.getAllUsers().size());

        }

    @Test

    public void getUserByUsername(){
        String userName = "UserName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";

        Long id = userService.addUser(userName, passwd, state, email);
        UserDto userDto = userService.getUserById(id);
        assertEquals(1,userService.getUserByUsername(userName).size());
        assertEquals(userName, userDto.getUserName());
        assertEquals(state, userDto.getState());
        assertEquals(email, userDto.getEmail());
        assertEquals(0,userService.getUserByUsername("999999999").size());
    }

}
