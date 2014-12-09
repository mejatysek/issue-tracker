/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.RoleDto;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {

    public Long addUser(String userName, String password, boolean state, Long role, String email);

    public Long addUser(String userName, String password, boolean state, Long role, String email, List<Long> issues);

    public Long addUser(String userName, String password, boolean state, Long role, String email, List<Long> issues, List<Long> operations);

    public Long addUser(String userName, String password, boolean state, Long role, String email, List<Long> issues, List<Long> operations, List<Long> customers);

    public void deactivateUser(Long userId);

    public Long editUser(UserDto user);

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id);

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers();

    @Transactional(readOnly = true)
    public List<UserDto> getUserByUsername(String username);

    @Transactional(readOnly = true)
    public List<UserDto> getUserByState(boolean state);

    @Transactional(readOnly = true)
    public List<UserDto> getUserByRole(RoleDto role);
}
