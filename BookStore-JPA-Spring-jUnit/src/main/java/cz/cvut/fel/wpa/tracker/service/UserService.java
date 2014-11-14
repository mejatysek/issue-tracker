/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.UserDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mickapa1
 */
@Transactional
public interface UserService {

    /**
     * Add user to the system
     * @param userName username
     * @param password password as an open text (will be stored in hashed form)
     * @param age age of the user
     * @return identifier of the user stored
     */
    public Long addUser(String userName, String password, int age);

    /**
     * Permanently removes the user
     * @param userId id of the user to be removed
     */
    public void deleteUser(Long userId);
    /**
     * Return user with the given id
     * @param id idenfier of the user to be retrieved
     * @return user with the given id, null if the user does not exist
     */
    @Transactional(readOnly=true)
    public UserDto getUserById(Long id);

    /**
     * Get all users stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<UserDto> getAllUsers();
}
