///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cz.cvut.fel.wpa.tracker.service;
//
//import cz.cvut.fel.wpa.tracker.dto.UserDto;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author mickapa1
// */
//public class UserServiceImplTest extends AbstractServiceTest{
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void testAddAndRetrieveUser() {
//
//        String userName = "UserName";
//        String passwd = "passwd";
//        int age = 18;
//
//        Long id = userService.addSalesman(userName, passwd, age);
//        UserDto userDto = userService.getUserById(id);
//
//        assertEquals(userName, userDto.getUserName());
//        assertEquals(age, userDto.getAge());
//
//    }
//    @Test
//    public void testAddAndRemoveUser() {
//
//        String userName = "UserName";
//        String passwd = "passwd";
//        int age = 18;
//
//        Long id = userService.addSalesman(userName, passwd, age);
//        assertEquals(1, userService.getAllUsers().size());
//        userService.deleteUser(id);
//        assertEquals(0, userService.getAllUsers().size());
//    }
//
//}
